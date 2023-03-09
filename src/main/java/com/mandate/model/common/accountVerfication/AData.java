package com.mandate.model.common.accountVerfication;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class AData {

	private List<Source> source;
	private String identifier;
}
