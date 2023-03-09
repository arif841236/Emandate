package com.mandate.model.common.accountVerfication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class Result {

	private AData data;
	private ComparisionData comparisionData;
}
