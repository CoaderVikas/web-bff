package com.bff.vikas.feign.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bff.vikas.feign.dto.request.FloorDto;
import com.bff.vikas.feign.dto.request.MetaConfigDto;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class       : PropertyResponseDTO
 * Description : Flat, component-aligned Response DTO optimized with conditional inclusion.
 * Author      : Vikas Yadav
 * Created On  : Jun 14, 2026
 * Version     : 3.1 (Flat Frozen Schema + Verification)
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyResponseDTO implements Serializable {
	// Core Identity & Reference Tokens
	private Long id;
	private String propertyId;
	private String userId;
	private String type;

	// Common Root Properties
	private String propertyName;
	private Integer floors;

	// Unified Address Coordinates
	private String address;
	private String addressLine;
	private String address1;
	private String address2;
	private String area;
	private String areaLocality;
	private String city;
	private String district;
	private String state;
	private String pincode;

	// Residential Metrics Layout
	private String bhkConfig;
	private String roomType;
	private String furnishing;
	private String floor;

	// Financial / Rental Precision Channels
	private BigDecimal monthlyRent;
	private BigDecimal securityDeposit;

	// Commercial Infrastructure Parameters
	private String unitType;
	private BigDecimal maintenanceCharges;
	private String washroom;
	private String parking;
	private String plotArea;

	// Complex Building Specifications
	private String buildingType;
	private Boolean hasLift;
	private Boolean hasParking;
	private Boolean hasPowerBackup;

	// Commercial Compliance Specs
	private String gstin;
	private String contactNumber;

	// Media Resource Endpoint
	private String image;

	@Builder.Default
	private List<String> images = new ArrayList<>();

	// Hierarchical Graph Datasets (For Complex: BUILDING, HOTEL, PG, etc.)
	private List<FloorDto> floorsData;

	// Dynamic Meta Schemes Custom Node (For Type: OTHER)
	private MetaConfigDto metaConfig;
	private String customType;

	private String verificationStatus; // PENDING / VERIFIED / REJECTED
	private String verifiedBy;
	private LocalDateTime verifiedAt;
	private String rejectionReason;

	// Audit Logs / System Trace Artifacts
	private String createdBy;
	private LocalDateTime createdDate;
	private String updatedBy;
	private LocalDateTime updatedDate;
	private Boolean isDeleted;

	// Open runtime metadata values payload mapping catch
	private Map<String, Object> additionalFields;

	@JsonAnyGetter
	public Map<String, Object> getAdditionalFields() {
		return additionalFields;
	}

	@JsonAnySetter
	public void addField(String key, Object value) {
		if (this.additionalFields == null) {
			this.additionalFields = new HashMap<>();
		}
		this.additionalFields.put(key, value);
	}
}