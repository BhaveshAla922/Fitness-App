package com.fitness.userservice.users.dto;

import java.util.List;
import lombok.Data;

@Data
public class PaginatedUsersResponse {

    private List<UsersResponse> data;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalRecords;
    
}
