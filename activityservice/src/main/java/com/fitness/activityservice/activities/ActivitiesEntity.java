package com.fitness.activityservice.activities;

import com.fitness.activityservice.enums.ActivityEnums.ActivityIntensity;
import com.fitness.activityservice.enums.ActivityEnums.ActivityStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "activities")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitiesEntity {

    private String id;

    private String userId;

    private String activityTypeId;

    private Duration duration;

    private Integer caloriesBurned;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Map<String, Object> additionalMetrics;

    private ActivityIntensity intensity;

    private ActivityStatus status;

    private String notes;

    private Integer version;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdById;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedById;

}
