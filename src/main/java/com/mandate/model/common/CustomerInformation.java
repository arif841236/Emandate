package com.mandate.model.common;

import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
@ApiModel(description="All details about the Customer Information.")
public class CustomerInformation {

	@NotEmpty(message = "Please enter name.")
	@ApiModelProperty(notes="Loan number should have only digit")
	private String loanNumber;
	
	@NotEmpty(message = "Please enter name.")
	@ApiModelProperty(notes="Name should have atleast 2 characters")
	private String fullName;
	
	@ApiModelProperty(notes="Mobile number should have only digit.")
	@Size(min = 10, max = 10, message = "Please enter valid mobile number.")
	@Pattern(regexp = "(0|91)?[6-9]\\d{9}", message = "Please enter valid mobile number.")
	@NotEmpty(message = "Please enter mobile number.")
	private String mobileNo;
	
	@ApiModelProperty(notes="email id should have activated.")
	@NotEmpty(message = "Please enter email id.")
	private String emailId;
	
	@ApiModelProperty(notes="Loan amount should have only digit.")
	private BigDecimal loanAmount;
	
}
