package com.mandate.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ACVerification {

	private String consent;
	private String ifsc;
	private String accountNumber;
}
