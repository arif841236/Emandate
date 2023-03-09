package com.mandate.controller;

import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mandate.common.MessageTypeConst;
import com.mandate.model.common.EmandateRequest;
import com.mandate.model.common.EmandateResponce;
import com.mandate.model.common.LocalDateAdapter;
import com.mandate.model.common.LoggingResponseMessage;
import com.mandate.service.IEmandateThirdApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * This is controller layer to take all endpoint and call the all method.
 * @author Md Arif
 *
 */

@RestController
@Api(description = "Emandate API", tags = "Emandate")
@Slf4j
public class EmandateController {

	GsonBuilder builder =new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe());
	Gson gson = builder.create();

	@Autowired
	IEmandateThirdApiService iEmandateThirdApiService;

	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "emandateRequest", value = "Fill the emandate details",required = true,dataType = "EmandateRequest")})
	@ApiOperation(notes = "Return Emandate response", value = "Set emandate",response = EmandateResponce.class)
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},value = "/set",name = "Emandate Request")
	public ResponseEntity<Object> setEmandate(@RequestBody() @Valid EmandateRequest emandateRequest){

		LoggingResponseMessage loggingResponseMessage = LoggingResponseMessage.builder()
				.data(emandateRequest)
				.message("Set emandate method start.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.CONTINUE)
				.build();

		log.info(gson.toJson(loggingResponseMessage));

		Object connectApi = iEmandateThirdApiService.connectApi(emandateRequest);

		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(connectApi)
				.message("Set emandate method end.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.statusCode(HttpStatus.CREATED)
				.build();

		log.info(gson.toJson(loggingResponseMessage2));

		return ResponseEntity.ok(connectApi);
	}

}
