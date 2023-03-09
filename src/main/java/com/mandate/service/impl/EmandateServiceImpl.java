package com.mandate.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mandate.common.MessageTypeConst;
import com.mandate.model.AccountVerificationData;
import com.mandate.model.EmandateData;
import com.mandate.model.common.LocalDateAdapter;
import com.mandate.model.common.LoggingResponseMessage;
import com.mandate.model.common.accVerification.ACVerificationResponse;
import com.mandate.repository.IAccountVerificationRepository;
import com.mandate.repository.IEmandateRepository;
import com.mandate.service.IEmandateService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This is service layer for emandate data save
 * @author Md Arif
 *
 */
@Service
@Slf4j
@NoArgsConstructor
public class EmandateServiceImpl implements IEmandateService{

	@Autowired
	IEmandateRepository iEmandateRepository;

	@Autowired
	IAccountVerificationRepository accountVerificationRepository;

	public EmandateServiceImpl(IEmandateRepository iEmandateRepository,IAccountVerificationRepository accountVerificationRepository) {
		super();
		this.iEmandateRepository = iEmandateRepository;
		this.accountVerificationRepository = accountVerificationRepository;
	}

	GsonBuilder builder =new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe());
	Gson gson = builder.create();

	/**
	 * This method is to save emandate data
	 */
	@Override
	public EmandateData save(EmandateData emandateData) {
		LoggingResponseMessage loggingResponseMessage = LoggingResponseMessage.builder()
				.data(emandateData)
				.message("save method start.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.CONTINUE)
				.build();

		log.info(gson.toJson(loggingResponseMessage));

		EmandateData emandateData2 = iEmandateRepository.save(emandateData);
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(emandateData)
				.message("Emandate data save successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.CONTINUE)
				.build();

		log.info(gson.toJson(loggingResponseMessage2));

		return emandateData2;
	}

	/**
	 * This method to save account verification data.
	 */
	@Override
	public AccountVerificationData save(ACVerificationResponse accountVerificationData) {
		LoggingResponseMessage loggingResponseMessage = LoggingResponseMessage.builder()
				.data(accountVerificationData)
				.message("save method start.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.CONTINUE)
				.build();

		log.info(gson.toJson(loggingResponseMessage));

		Map<String, Object> data = new HashMap<>();
		data.put("verification data", accountVerificationData);
		List<AccountVerificationData> findAll = accountVerificationRepository.findAll();

		if(!findAll.isEmpty()) {
			Collection<Object> values = findAll.get(0).getVerificationResponse().values();
			List<Object> obj = values.stream().toList();
			ACVerificationResponse fromJson = gson.fromJson(obj.get(0).toString(), ACVerificationResponse.class);
			log.info(gson.toJson(fromJson));
		}

		AccountVerificationData build = null;
		if(accountVerificationData != null) {
			build = AccountVerificationData.builder()
					.accountNumber(accountVerificationData.getResult().getAccountNumber())
					.date(LocalDateTime.now().toString())
					.verificationResponse(data)
					.build();
		}

		AccountVerificationData emandateData =null;
		if(build != null) {
			emandateData = accountVerificationRepository.save(build);
		}

		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(emandateData)
				.message("Emandate data save successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.CONTINUE)
				.build();

		log.info(gson.toJson(loggingResponseMessage2));

		return emandateData;
	}
}
