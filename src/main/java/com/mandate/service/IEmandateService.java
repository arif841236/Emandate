package com.mandate.service;

import com.mandate.model.AccountVerificationData;
import com.mandate.model.EmandateData;
import com.mandate.model.common.accVerification.ACVerificationResponse;

public interface IEmandateService {

	public EmandateData save(EmandateData emandateData);
	public AccountVerificationData save(ACVerificationResponse accountVerificationData);
}
