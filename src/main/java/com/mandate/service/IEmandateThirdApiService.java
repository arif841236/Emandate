package com.mandate.service;

import com.mandate.model.common.EmandateRequest;
import com.mandate.model.common.EmandateResponce;

public interface IEmandateThirdApiService {

	public EmandateResponce connectApi(EmandateRequest emandateRequest);
}
