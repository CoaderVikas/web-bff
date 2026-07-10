package com.bff.vikas.feign.dto.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class       : VerificationActionRequest
 * Description : BFF-side request body for the admin reject action. Mirrors the
 *               Property Service DTO. Carries the human-readable reason shown to
 *               the owner on a REJECTED property. (Verify needs no body.)
 *
 * Author      : Vikas Yadav
 * Created On  : Jul 10, 2026
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationActionRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Why the property was rejected. Required. */
    private String reason;
}