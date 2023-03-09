package com.mandate.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mandate.common.EmandateType;
import com.mandate.common.MessageTypeConst;
import com.mandate.model.EmandateData;
import com.mandate.model.common.EmandateRequest;
import com.mandate.model.common.EmandateResponce;
import com.mandate.model.common.LocalDateAdapter;
import com.mandate.model.common.LoggingResponseMessage;
import com.mandate.model.common.accVerification.ACVerificationResponse;
import com.mandate.model.common.accountVerfication.AData;
import com.mandate.model.common.accountVerfication.AccountVerificationResponse;
import com.mandate.service.IEmandateService;
import com.mandate.service.IEmandateThirdApiService;
import com.mandate.util.Util;
import lombok.extern.slf4j.Slf4j;

/**
 * This is service layer to connect 3rd party api for emandate.
 *
 */
@Service
@Slf4j
public class EmandateThirdApiServiceImpl implements IEmandateThirdApiService{

	@Autowired
	IEmandateService iEmandateService;

	@Autowired
	Util util;

	public EmandateThirdApiServiceImpl(IEmandateService iEmandateService, Util util) {
		super();
		this.iEmandateService = iEmandateService;
		this.util = util;
	}

	GsonBuilder builder =new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe());
	Gson gson = builder.create();

	/**
	 * This method to connect 3rd party api for emandate.
	 */
	@SuppressWarnings("unused")
	@Override
	public EmandateResponce connectApi(EmandateRequest emandateRequest) {
		LoggingResponseMessage loggingResponseMessage = LoggingResponseMessage.builder()
				.data(emandateRequest)
				.message("connectApi method start.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.ACCEPTED)
				.build();

		log.info(gson.toJson(loggingResponseMessage));

		boolean isValid = false;
		ACVerificationResponse accountVerfication = null;
		AccountVerificationResponse accountVerificationResponse = null;
		if(emandateRequest.getDebitAccountDeatils().getCreateMandateWith().equals(EmandateType.AADHAAR_NUMBER)) {
			LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
					.message("Emandate verification start.")
					.messageTypeId(MessageTypeConst.SUCCESS)
					.statusCode(HttpStatus.CONTINUE)
					.build();

			log.info(gson.toJson(loggingResponseMessage2));

			accountVerfication = util.accountVerfication(emandateRequest);

			iEmandateService.save(accountVerfication);
			
			LoggingResponseMessage loggingResponseMessage3 = LoggingResponseMessage.builder()
					.message("Emandate verification end.")
					.data(accountVerfication)
					.messageTypeId(MessageTypeConst.SUCCESS)
					.statusCode(HttpStatus.CONTINUE)
					.build();

			log.info(gson.toJson(loggingResponseMessage3));

			if(accountVerfication != null){
				isValid = accountVerfication.getResult().isBankTxnStatus();
			}

			if(accountVerificationResponse != null) {
				AData result = accountVerificationResponse.getResult().getData();
				if(result != null && !result.getSource().isEmpty()) {
					isValid = result.getSource().get(0).getData().isBankTxnStatus();
				}
			}
		}

		EmandateData emandateData = null;
		EmandateResponce response = null ;

		if(isValid && accountVerfication != null) {

			LoggingResponseMessage loggingResponseMessage3 = LoggingResponseMessage.builder()
					.message("Emandate data creattion start.")
					.data(accountVerfication)
					.messageTypeId(MessageTypeConst.SUCCESS)
					.statusCode(HttpStatus.CONTINUE)
					.build();

			log.info(gson.toJson(loggingResponseMessage3));

			String key = accountVerfication.getRequestId();

			emandateData = EmandateData.builder()
					.debitAccountNo(emandateRequest.getDebitAccountDeatils().getDebitAccountNo())
					.loanNumber(emandateRequest.getCustomerInformation().getLoanNumber())
					.emandateStatus("success")
					.ifscCode(emandateRequest.getDebitAccountDeatils().getIfscCode())
					.mobileNo(emandateRequest.getCustomerInformation().getMobileNo())
					.name(emandateRequest.getCustomerInformation().getFullName())
					.requestId(key)
					.verificationDate(LocalDateTime.now().toString())
					.emandateType(emandateRequest.getDebitAccountDeatils().getCreateMandateWith().name())
					.build();

			EmandateData emandateData2 = iEmandateService.save(emandateData);

			LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
					.data(emandateData2)
					.message("Emandate data save successfully.")
					.messageTypeId(MessageTypeConst.SUCCESS)
					.statusCode(HttpStatus.CONTINUE)
					.build();

			log.info(gson.toJson(loggingResponseMessage2));

			response = EmandateResponce.builder()
					.message("successfully")
					.statusCode(HttpStatus.CREATED.value())
					.build();
		}
		else{

			String key = null;
			if(accountVerfication != null) {
				key = accountVerfication.getRequestId();
			}

			emandateData = EmandateData.builder()
					.debitAccountNo(emandateRequest.getDebitAccountDeatils().getDebitAccountNo())
					.loanNumber(emandateRequest.getCustomerInformation().getLoanNumber())
					.emandateStatus("failed")
					.ifscCode(emandateRequest.getDebitAccountDeatils().getIfscCode())
					.mobileNo(emandateRequest.getCustomerInformation().getMobileNo())
					.name(emandateRequest.getCustomerInformation().getFullName())
					.requestId(key)
					.verificationDate(LocalDateTime.now().toString())
					.emandateType(emandateRequest.getDebitAccountDeatils().getCreateMandateWith().name())
					.build();

			EmandateData emandateData2 = iEmandateService.save(emandateData);

			LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
					.data(emandateData2)
					.message("Emandate data save successfully.")
					.messageTypeId(MessageTypeConst.ERROR)
					.statusCode(HttpStatus.UNPROCESSABLE_ENTITY)
					.build();

			log.info(gson.toJson(loggingResponseMessage2));

			response = EmandateResponce.builder()
					.message("Failed")
					.statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
					.build();
		}
		return	response;
	}
}
