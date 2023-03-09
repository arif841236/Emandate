package com.mandate.model.common;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
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
@ApiModel(description="All details about the mandate Information.")
public class MandateInformation {

	@NotEmpty(message = "Please enter the collection amount.")
	@ApiModelProperty(notes="Fill the collection amount.")
	private BigDecimal collectionAmount;
	
	@NotEmpty(message = "Please enter the maximum collection amount.")
	@ApiModelProperty(notes="Fill the maximum collection amount.")
	private BigDecimal maximumCollectionAmount;
	
	@NotEmpty(message = "Please enter the collection type.")
	@ApiModelProperty(notes="Fill the collection type.")
	private String collectionType;
	
	@NotEmpty(message = "Please enter the collection frequency.")
	@ApiModelProperty(notes="Fill the collection frequency.")
	private String collectionFrequency;
	
	@NotEmpty(message = "Please enter the collection first date.")
	@ApiModelProperty(notes="Fill the collection first date.")
	private LocalDate collectionFirstDate;
	
	@NotEmpty(message = "Please enter the collection last date.")
	@ApiModelProperty(notes="Fill the collection last date.")
	private LocalDate collectionLastDate;
	
}
