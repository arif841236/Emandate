package com.mandate.util;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mandate.common.MessageTypeConst;
import com.mandate.model.common.ACVerification;
import com.mandate.model.common.AccountVerificationRequest;
import com.mandate.model.common.EmandateRequest;
import com.mandate.model.common.LocalDateAdapter;
import com.mandate.model.common.LoggingResponseMessage;
import com.mandate.model.common.accVerification.ACVerificationResponse;
import com.mandate.model.common.accountVerfication.AccountVerificationResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Util {

	GsonBuilder builder =new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe());
	Gson gson = builder.create();

	@Value("${uri.bankacc}")
	String uri ;

	@Value("${header.key}")
	String key;

	@Value("${header.value}")
	String value;


	public ACVerificationResponse accountVerfication(EmandateRequest emandateRequest) {

		LoggingResponseMessage loggingResponseMessage3 = LoggingResponseMessage.builder()
				.message("Emandate account verification start.")
				.data(emandateRequest)
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.CONTINUE)
				.build();

		log.info(gson.toJson(loggingResponseMessage3));

		ACVerification acVerification = ACVerification.builder()
				.accountNumber(emandateRequest.getDebitAccountDeatils().getDebitAccountNo())
				.consent("Y")
				.ifsc(emandateRequest.getDebitAccountDeatils().getIfscCode())
				.build();

		LoggingResponseMessage loggingResponseMessage4 = LoggingResponseMessage.builder()
				.message("Account verification request body completed.")
				.data(acVerification)
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.CONTINUE)
				.build();

		log.info(gson.toJson(loggingResponseMessage4));

		HttpHeaders headers = new HttpHeaders();
		headers.add(key, value);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ACVerification> entity = new HttpEntity<>(acVerification, headers);
		ResponseEntity<ACVerificationResponse> cardDetailsResponse = new RestTemplate()
				.exchange(uri, HttpMethod.POST, entity, ACVerificationResponse.class);

		LoggingResponseMessage loggingResponseMessage5 = LoggingResponseMessage.builder()
				.message("Account verification completed.")
				.data(cardDetailsResponse.getBody())
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.CONTINUE)
				.build();

		log.info(gson.toJson(loggingResponseMessage5));

		return cardDetailsResponse.getBody();
	}

	public AccountVerificationResponse accountVerfication2(EmandateRequest emandateRequest) {
		LoggingResponseMessage loggingResponseMessage3 = LoggingResponseMessage.builder()
				.message("Emandate account verification start.")
				.data(emandateRequest)
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.CONTINUE)
				.build();

		log.info(gson.toJson(loggingResponseMessage3));

		AccountVerificationRequest accountVerificationRequest = AccountVerificationRequest.builder()
				.accountHolderName(emandateRequest.getCustomerInformation().getFullName())
				.accountNumber(emandateRequest.getDebitAccountDeatils().getDebitAccountNo())
				.consent("Y")
				.allowPartialMatch(true)
				.useCombinedSolution("Y")
				.ifsc(emandateRequest.getDebitAccountDeatils().getIfscCode())
				.nameMatchType("Entity")
				.build();

		LoggingResponseMessage loggingResponseMessage4 = LoggingResponseMessage.builder()
				.message("Account verification request body completed.")
				.data(accountVerificationRequest)
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.CONTINUE)
				.build();

		log.info(gson.toJson(loggingResponseMessage4));

		HttpHeaders headers = new HttpHeaders();
		headers.add(key, value);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AccountVerificationRequest> entity = new HttpEntity<>(accountVerificationRequest, headers);

		ResponseEntity<AccountVerificationResponse> cardDetailsResponse = new RestTemplate()
				.exchange(uri, HttpMethod.POST, entity, AccountVerificationResponse.class);

		LoggingResponseMessage loggingResponseMessage5 = LoggingResponseMessage.builder()
				.message("Account verification completed.")
				.data(cardDetailsResponse.getBody())
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.CONTINUE)
				.build();

		log.info(gson.toJson(loggingResponseMessage5));

		return cardDetailsResponse.getBody();
	}
}
