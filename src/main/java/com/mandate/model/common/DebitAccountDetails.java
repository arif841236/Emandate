package com.mandate.model.common;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import com.mandate.common.EmandateType;
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
@ApiModel(description="All details about the Debit account details.")
public class DebitAccountDetails {

	
	@NotEmpty(message = "Please enter valid emandate type.")
	@ApiModelProperty(notes="Write the create mandate with.")
	@Enumerated(EnumType.STRING)
	private EmandateType createMandateWith;
	
	@NotEmpty(message = "Please enter bank name.")
	@ApiModelProperty(notes="Fill the bank name.")
	private String  selectBank;
	
	@NotEmpty(message = "Please enter bank account number.")
	@ApiModelProperty(notes="Fill the bank account number.")
	private String debitAccountNo;
	
	@NotEmpty(message = "Please enter account type.")
	@ApiModelProperty(notes="Fill the account type.")
	private String accountType;
	
	@NotEmpty(message = "Please enter bank IFSC code.")
	@ApiModelProperty(notes="Fill the bank IFSC code.")
	private String ifscCode;
}
