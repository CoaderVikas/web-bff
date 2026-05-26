package com.bff.vikas.feign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : ChangePasswordResponse
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Mar 5, 2026
 * Version    : 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordResponse {

    private boolean success;
    private String message;

}
