package com.mandate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class EmandateData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ; 
	private String name; 
	private String requestId;
	
	@Size(min = 10, max = 10, message = "Please enter valid mobile number")
	@Pattern(regexp = "(0|91)?[6-9]\\d{9}", message = "Please enter valid mobile number")
	@NotEmpty(message = "Please enter mobile number")
	private String mobileNo;
	private String debitAccountNo;
	private String ifscCode;
	private String loanNumber;
	private String emandateType;
	private String emandateStatus;
	private String verificationDate;
}
