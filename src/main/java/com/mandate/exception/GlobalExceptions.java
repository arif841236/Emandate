package com.mandate.exception;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.mandate.common.MessageTypeConst;
import com.mandate.model.common.LoggingResponseMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * This is Global exception handler class
 * its handle all type of exception
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptions extends ResponseEntityExceptionHandler {

	@Autowired
	Gson gson; 
	
	@Value("${some.error}")
	String message;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
		ErrorResponse response = ErrorResponse.builder()
				.message(fieldError.get(0).getDefaultMessage())
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();

		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message("Some data type errror.")
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));

		return ResponseEntity.internalServerError().body(response);
	}

	@ExceptionHandler(SQLException.class)
	public final ResponseEntity<Object> sqlExceptions(SQLException ex, WebRequest request) {
		ErrorResponse error = ErrorResponse.builder()
				.message(ex.getMessage())
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();

		LoggingResponseMessage msgStart = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.messageTypeId(MessageTypeConst.ERROR)
				.data(error)
				.build();

		log.error(gson.toJson(msgStart));

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmandateException.class)
	public ErrorResponse handleRecordFoundException(EmandateException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(TransactionSystemException.class)
	public ErrorResponse handleTransactionException(TransactionSystemException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ErrorResponse handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ClassCastException.class)
	public ErrorResponse handleClassCastException(ClassCastException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(JpaSystemException.class)
	public ErrorResponse handleJpaSystemException(JpaSystemException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public ErrorResponse handleNullPointerException(NullPointerException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(JsonIOException.class)
	public ErrorResponse handleJsonIOException(JsonIOException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(ex.getMessage())
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();

		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		
		return ResponseEntity.internalServerError().body(response);
	}
	
}
