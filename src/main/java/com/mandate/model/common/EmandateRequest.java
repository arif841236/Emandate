package com.mandate.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(description="All details about the emandate.")
public class EmandateRequest {

	@ApiModelProperty(notes="Fill the customer information.")
	private CustomerInformation customerInformation;

	@ApiModelProperty(notes="Fill the debit account details.")
	private DebitAccountDetails debitAccountDeatils;

	@ApiModelProperty(notes="Fill the mandate information.")
	private MandateInformation mandateInformation;

}
