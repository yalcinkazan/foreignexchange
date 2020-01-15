package com.exchange.foreignexchange;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.exchange.foreignexchange.controller.MainController;
import com.exchange.foreignexchange.enums.CurrencyTypes;
import com.exchange.foreignexchange.util.CommonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ForeignExchangeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private MainController mainController;
	
	@Value("${date.format}")
	private String DATE_FORMAT;
	
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.mainController).build();
		
	}
	
	@Test
    public void rateResponseSizeTest() throws Exception {
		  String json = "{\n" +
	                "  \"source\": \"" + CurrencyTypes.EUR.toString() + "\",\n" +
	                "  \"target\": \"" + CurrencyTypes.TRY.toString() + "\"\n" +
	                "}";
		  
		  this.mockMvc.perform(post("/rate").contentType(MediaType.APPLICATION_JSON).content(json))
	                .andExpect(jsonPath("$.*", Matchers.hasSize(1)));
    }
	
	@Test
    public void sameCurrencyRateTest() throws Exception {
		 String json = "{  \"source\": \"" + CurrencyTypes.TRY.toString() + "\",\n" +
		                "  \"target\": \"" + CurrencyTypes.TRY.toString() + "\"\n" +
		                "}";
		  
		 String response = this.mockMvc
						.perform(post("/rate").content(json).contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andReturn()
						.getResponse()
						.getContentAsString();

			assertThat(response).isEqualTo("{\"exchangeRate\":"+ 1.0 + "}");
    }
	
	@Test
    public void calculateConversionTest() throws Exception {
		 String json = "{  \"sourceAmount\": \"" + 5 + "\",\n" +
		                "  \"sourceCurrency\": \"" + CurrencyTypes.EUR.toString() + "\",\n" +
		                "  \"targetCurrency\": \"" + CurrencyTypes.TRY.toString() + "\"\n" +
		                "}";
		  
		 String response = this.mockMvc
					.perform(post("/conversion").content(json).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andReturn()
					.getResponse()
					.getContentAsString();
		 
			assertThat(response).contains("transactionId", "amountInTargetCurrency", "date");
    }
	
	@Test
    public void postConversionThenGetByIdTest() throws Exception {
		
		 String conversionJson = "{  \"sourceAmount\": \"" + 5 + "\",\n" +
				                  "  \"sourceCurrency\": \"" + CurrencyTypes.EUR.toString() + "\",\n" +
				                  "  \"targetCurrency\": \"" + CurrencyTypes.TRY.toString() + "\"\n" +
				                  "}";
	  
		 this.mockMvc.perform(post("/conversion")
					 .content(conversionJson)
					 .contentType(MediaType.APPLICATION_JSON))
					 .andExpect(status().isOk())
					 .andReturn();
		
		
		
		 String json = "{  \"transactionId\": \"" + 1 + "\"\n" + "}";
		  
		 String response = this.mockMvc
						.perform(get("/conversions").content(json).contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andReturn()
						.getResponse()
						.getContentAsString();

		 assertThat(response).contains("transactionId", "amountInTargetCurrency", "date");
    }
	
	@Test
    public void postConversionThenGetByDatePaginationTest() throws Exception {
		
		 String conversionJson = "{  \"sourceAmount\": \"" + 5 + "\",\n" +
				                  "  \"sourceCurrency\": \"" + CurrencyTypes.EUR.toString() + "\",\n" +
				                  "  \"targetCurrency\": \"" + CurrencyTypes.TRY.toString() + "\"\n" +
				                  "}";
	  
		 this.mockMvc.perform(post("/conversion")
					 .content(conversionJson)
					 .contentType(MediaType.APPLICATION_JSON))
					 .andExpect(status().isOk())
					 .andReturn();
		
		
		
		 String json = "{  \"date\": \"" + new SimpleDateFormat(this.DATE_FORMAT).format(CommonUtil.getToday()) + "\",\n" +
	                   "  \"pageNumber\": \"" + 1 + "\"\n" +
	                   "}";
		 
		 String response = this.mockMvc
						.perform(get("/conversions").content(json).contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andReturn()
						.getResponse()
						.getContentAsString();

		 assertThat(response).contains("transactionId", "amountInTargetCurrency", "date");
    }

}
