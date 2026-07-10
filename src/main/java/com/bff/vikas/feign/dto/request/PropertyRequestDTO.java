package com.bff.vikas.feign.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : PropertyRequestDTO
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 14, 2026
 * Version    : 1.0
 */

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyRequestDTO implements Serializable {
	private String type;

	// Common
	private String propertyName;
	private String floors;

	// Address
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

	// Residential
	private String bhkConfig;
	private String roomType;
	private String furnishing;
	private String floor;

	// Rental
	private BigDecimal monthlyRent;
	private BigDecimal securityDeposit;

	// Commercial
	private String unitType;
	private BigDecimal maintenanceCharges;
	private String washroom;
	private String parking;

	// Building
	private String buildingType;
	private Boolean hasLift;
	private Boolean hasParking;
	private Boolean hasPowerBackup;

	// Hotel
	private String gstin;
	private String contactNumber;

	// Images
	private String image;
	@Builder.Default
	private List<String> images = new ArrayList<>();

	// Multi Unit
	private List<FloorDto> floorsData;

	// Dynamic Property
	private MetaConfigDto metaConfig;
	private String customType;

	// Capture unknown future fields
	@Builder.Default
	private Map<String, Object> additionalFields = new HashMap<>();

	@JsonAnySetter
	public void addField(String key, Object value) {
		if (this.additionalFields == null) {
			this.additionalFields = new HashMap<>();
		}
		this.additionalFields.put(key, value);
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalFields() {
	    return additionalFields;
	}
}