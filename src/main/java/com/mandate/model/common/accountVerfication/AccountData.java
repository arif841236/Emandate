package com.mandate.model.common.accountVerfication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountData {

	private boolean bankTxnStatus;
	private String accountNumber;
	private String ifsc;
	private String accountName;
	private String bankResponse;
	private String bankRRN;
	private String statusCode;
}
