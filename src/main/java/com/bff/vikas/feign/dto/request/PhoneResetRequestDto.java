package com.bff.vikas.feign.dto.request;

import lombok.Data;

/**
 * Class      : PhoneResetRequestDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jul 11, 2026
 * Version    : 1.0
 */

@Data
public class PhoneResetRequestDto {
    private String firebaseIdToken;
    private String newPassword;
}
