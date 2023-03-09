package com.mandate.model.common.accountVerfication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InputVsSource {

	private Flags flags;
	private String validity;
}
