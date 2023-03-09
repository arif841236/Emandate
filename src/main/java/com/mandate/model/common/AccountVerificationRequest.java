package com.mandate.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountVerificationRequest {
	private String consent;
	private String accountNumber;
	private String accountHolderName;
	private String ifsc;
	private String nameMatchType;
	private String useCombinedSolution;
	private boolean allowPartialMatch;
}
