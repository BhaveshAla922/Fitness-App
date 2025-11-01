package com.fitness.userservice.base;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class BaseService {
    private static final Integer DEFAULT_PAGE_NUM = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_SORT_BY = "id:ASC";

    private static List<String> sortableFields = new ArrayList<>();
    private static List<String> filterableFields = new ArrayList<>();

    public static <T, F extends PaginationConfig> PaginateResult<T> paginate(Class<T> entityClass, F filterRequest) {
        Integer page = DEFAULT_PAGE_NUM - 1;
        Integer limit = DEFAULT_PAGE_SIZE;
        String sortBy = DEFAULT_SORT_BY;
        Map<String, String> filters = new java.util.LinkedHashMap<>();

        if (filterRequest != null) {
            sortableFields = Arrays.asList(filterRequest.getSortableFields());
            filterableFields = Arrays.asList(filterRequest.getFilterableFields());

            for (var field : filterRequest.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                try {
                    Object value = field.get(filterRequest);
                    if (name.equals("page")) {
                        if (value instanceof Integer && ((Integer) value) > 0) {
                            page = ((Integer) value) - 1;
                        }
                    } else if (name.equals("limit")) {
                        if (value instanceof Integer && ((Integer) value) > 0) {
                            limit = (Integer) value;
                        }
                    } else if (name.equals("sortBy")) {
                        if (value instanceof String && !((String) value).isEmpty()) {
                            sortBy = (String) value;
                        }
                    } else {
                        if (value != null && !(value instanceof String && ((String) value).isEmpty())) {
                            filters.put(name, value.toString());
                        }
                    }
                } catch (Exception ignored) {}
            }
        }

        Sort sortObj = parseSorting(entityClass, sortBy);
        Pageable pageable = PageRequest.of(page, limit, sortObj);
        Specification<T> spec = parseFilters(entityClass, filters);

        return new PaginateResult<>(pageable, spec);
    }

    public interface PaginationConfig {
        String[] getSortableFields();
        String[] getFilterableFields();
    }

    public static class PaginateResult<T> {
        public final Pageable pageable;
        public final Specification<T> specification;

        public PaginateResult(Pageable pageable, Specification<T> specification) {
            this.pageable = pageable;
            this.specification = specification;
        }
    }

    private static Sort parseSorting(Class<?> entityClass, String sortBy) {
        Map<String, String> sortMap = extractMapFromString(sortBy);
        List<Sort.Order> orders = new ArrayList<>();

        for (var entry : sortMap.entrySet()) {
            String fieldName = entry.getKey();
            String order = entry.getValue().toUpperCase();

            if (fieldName.isEmpty() || (!order.equals("ASC") && !order.equals("DESC"))) {
                throw new IllegalArgumentException("Invalid sort format: " + fieldName + "=" + order);
            }

            if (!sortableFields.contains(fieldName)) {
                throw new IllegalArgumentException("Sorting by " + fieldName + " is not valid");
            }

            orders.add(new Sort.Order(Sort.Direction.fromString(order), fieldName));
        }

        return Sort.by(orders);
    }

    public static <T> Specification<T> parseFilters(Class<T> entityClass, Map<String, String> filters) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (var filter : filters.entrySet()) {
                String fieldName = filter.getKey();

                if (!filterableFields.contains(fieldName)) {
                    throw new IllegalArgumentException("Filtering by " + fieldName + " is not valid");
                }

                Integer colonIdx = filter.getValue().indexOf(":");

                if (colonIdx == -1) {
                    throw new IllegalArgumentException("Invalid filter format: " + filter.getKey() + "=" + filter.getValue());
                }

                String filterOperation = filter.getValue().substring(0, colonIdx);
                Object filterValue = filter.getValue().substring(colonIdx + 1);

                switch (filterOperation) {
                    case "$eq":
                        predicates.add(cb.equal(root.get(fieldName), filterValue));
                        break;
                    case "$contains":
                        predicates.add(cb.like(root.get(fieldName), "%" + filterValue + "%"));
                        break;
                    case "$startsWith":
                        predicates.add(cb.like(root.get(fieldName), filterValue + "%"));
                        break;
                    case "$endsWith":
                        predicates.add(cb.like(root.get(fieldName), "%" + filterValue));
                        break;
                    case "$gt": {
                        try {
                            Integer intValue = Integer.parseInt((String) filterValue);
                            predicates.add(cb.greaterThan(root.get(fieldName), intValue));
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Filter value for $gt must be an integer", e);
                        }
                        break;
                    }
                    case "$lt": {
                        try {
                            Integer intValue = Integer.parseInt((String) filterValue);
                            predicates.add(cb.lessThan(root.get(fieldName), intValue));
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Filter value for $lt must be an integer", e);
                        }
                        break;
                    }
                    case "$in":
                        if (filterValue == null) {
                            throw new IllegalArgumentException("Filter value for $in cannot be null");
                        }
                        if (filterValue instanceof String) {
                            String[] vals = ((String) filterValue).split(",");
                            predicates.add(root.get(fieldName).in(Arrays.asList(vals)));
                        } else if (filterValue instanceof String[]) {
                            predicates.add(root.get(fieldName).in(Arrays.asList((String[]) filterValue)));
                        } else {
                            throw new IllegalArgumentException("Filter value for $in must be a comma-separated String or String[]");
                        }
                        break;
                    case "$isNull":
                        predicates.add(cb.isNull(root.get(fieldName)));
                        break;
                    case "$isNotNull":
                        predicates.add(cb.isNotNull(root.get(fieldName)));
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported filter operation: " + filterOperation);
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }


    private static Map<String, String> extractMapFromString(String input) {
        Map<String, String> map = new java.util.LinkedHashMap<>();

        if (input == null || input.trim().isEmpty()) {
            return map;
        }

        for (String pair : input.split("&")) {
            String[] parts = pair.split(":", 2);

            if (parts.length == 2) {
                map.put(parts[0].trim(), parts[1].trim());
            }
        }

        return map;
    }
}
