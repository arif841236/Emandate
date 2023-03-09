package com.mandate.exception;

import com.mandate.common.MessageTypeConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorResponse {
	private String message;
    private MessageTypeConst messageTypeId;
    private Integer statusCode;
}
