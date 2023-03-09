package com.mandate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mandate.common.EmandateType;
import com.mandate.model.common.CustomerInformation;
import com.mandate.model.common.DebitAccountDetails;
import com.mandate.model.common.EmandateRequest;
import com.mandate.model.common.EmandateResponce;
import com.mandate.model.common.MandateInformation;
import com.mandate.repository.IAccountVerificationRepository;
import com.mandate.repository.IEmandateRepository;
import com.mandate.service.IEmandateService;
import com.mandate.service.IEmandateThirdApiService;
import com.mandate.service.impl.EmandateServiceImpl;
import com.mandate.service.impl.EmandateThirdApiServiceImpl;
import com.mandate.util.Util;

/**
 * This is test class for testing emandate api.
 * @author Md Arif
 *
 */
@SpringBootTest
class MandateApplicationTests {

	@Autowired
	IEmandateService iEmandateService;

	@Autowired
	Util util;

	@Autowired
	IEmandateThirdApiService iEmandateThirdApiService;

	@MockBean
	IEmandateRepository iEmandateRepository;
	
	@MockBean
	IAccountVerificationRepository accountVerificationRepository;

	@BeforeEach
	void set() {
		this.iEmandateThirdApiService = new EmandateThirdApiServiceImpl(iEmandateService, util);
		this.iEmandateService = new EmandateServiceImpl(iEmandateRepository,accountVerificationRepository);
	}

	@Test
	void contextLoads() {
		EmandateRequest build = EmandateRequest.builder()
				.customerInformation(CustomerInformation.builder()
						.emailId("arif841236@gmail.com")
						.fullName("Mohammad Arif")
						.loanAmount(new BigDecimal("12000.50"))
						.loanNumber("856756")
						.mobileNo("8659676968")
						.build())
				.debitAccountDeatils(DebitAccountDetails.builder()
						.accountType("saving")
						.createMandateWith(EmandateType.AADHAAR_NUMBER)
						.debitAccountNo("3312741853")
						.ifscCode("CBIN0283570")
						.selectBank("CBI")
						.build())
				.mandateInformation(MandateInformation.builder()
						.collectionAmount(new BigDecimal("56873.87"))
						.collectionFirstDate(LocalDate.now().minusMonths(2))
						.collectionFrequency("monthly")
						.collectionLastDate(LocalDate.now().plusMonths(12))
						.collectionType("variable")
						.maximumCollectionAmount(new BigDecimal("1234855.89"))
						.build())
				.build();

		EmandateResponce connectApi = iEmandateThirdApiService.connectApi(build);
		assertEquals(422, connectApi.getStatusCode());
	}

}
