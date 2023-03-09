package com.mandate.model.common.accVerification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ACVerificationResponse {

	@JsonProperty("status-code")
	private String statusCode;
	
	@JsonProperty("request_id")
	private String requestId;
	
    private ACResult result;
}
