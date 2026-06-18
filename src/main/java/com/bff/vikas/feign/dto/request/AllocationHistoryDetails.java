package com.bff.vikas.feign.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Class      : AllocationHistoryDetails
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 15, 2026
 * Version    : 1.0
 */

@Data
@Builder
public class AllocationHistoryDetails {
    private int floorNo;
    private String roomNumber;
    private String bedName;
    private String tenantName;
    private String checkInDate;
    private String checkOutDate;
}
