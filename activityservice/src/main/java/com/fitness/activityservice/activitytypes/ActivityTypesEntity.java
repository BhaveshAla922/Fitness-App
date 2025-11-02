package com.fitness.activityservice.activitytypes;

import com.fitness.activityservice.enums.ActivityEnums.ActivityTypeStatus;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;


@Data
public class ActivityTypesEntity {

    private String id;

    private String name;

    private String description;

    private String category;

    private Map<String, Object> suggestedMetrics;

    private ActivityTypeStatus status;

    private Integer version;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
}
