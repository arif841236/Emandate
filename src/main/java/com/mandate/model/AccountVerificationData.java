package com.mandate.model;

import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.mandate.model.common.JSONObjectConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountVerificationData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String accountNumber;
	
	private String date;
	
	@Column(columnDefinition = "json")
	@Convert(attributeName = "data", converter = JSONObjectConverter.class)
	private Map<String, Object> verificationResponse;
}
