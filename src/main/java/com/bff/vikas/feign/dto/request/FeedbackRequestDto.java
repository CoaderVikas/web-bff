package com.bff.vikas.feign.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Class      : FeedbackRequestDto
 * Description: DTO for owner to submit feedback/rating for a tenant.
 *              allocationId links the feedback to a specific stay.
 * Author     : Vikas Yadav
 * Created On : Jun 18, 2026
 * Version    : 1.0
 */
@Data
public class FeedbackRequestDto {

    @NotBlank(message = "Tenant Custom ID is required")
    private String tenantCustomId;

    /**
     * Allocation ID of the specific stay this feedback is for.
     * Prevents duplicate feedback for same stay.
     */
    @NotNull(message = "Allocation ID is required")
    private Long allocationId;

    @NotBlank(message = "Property ID is required")
    private String propertyId;

    @NotNull(message = "Rating is required")
    @DecimalMin(value = "1.0", message = "Rating must be at least 1.0")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0")
    private Double rating;

    @Size(max = 1000, message = "Comment cannot exceed 1000 characters")
    private String comment;
}