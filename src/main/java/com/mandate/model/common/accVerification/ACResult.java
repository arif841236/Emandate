package com.mandate.model.common.accVerification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ACResult {

	private boolean bankTxnStatus;
	private String accountNumber;
	private String ifsc;
	private String accountName;
	private String bankResponse;
}
