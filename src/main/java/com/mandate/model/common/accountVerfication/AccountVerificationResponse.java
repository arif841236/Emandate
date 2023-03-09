package com.mandate.model.common.accountVerfication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountVerificationResponse {
	
	private Integer statusCode;
	private String requestId;
    private Result result;
    
}
