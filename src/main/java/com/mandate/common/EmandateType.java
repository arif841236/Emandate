package com.mandate.common;

import lombok.Getter;

@Getter
public enum EmandateType {

	AADHAAR_NUMBER(0),
	NET_BANKING(1),
	DEBIT_CARD(2);
	
	private Integer index;
    private EmandateType(Integer messageType) {
        this.index=messageType;
    }
}
