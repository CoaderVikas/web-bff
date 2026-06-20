package com.bff.vikas.feign.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * Class      : FeedbackResponseDto
 * Description: Response DTO for a single owner feedback entry on a tenant.
 *              Shown in tenant's history — visible to any future owner.
 * Author     : Vikas Yadav
 * Created On : Jun 18, 2026
 * Version    : 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedbackResponseDto {

    private Long feedbackId;

    /**
     * Owner who gave this feedback.
     * We show userId — owner name can be fetched from user-service if needed.
     */
    private String ownerUserId;

    private String propertyId;

    private Double rating;

    private String comment;

    private String feedbackDate;
}