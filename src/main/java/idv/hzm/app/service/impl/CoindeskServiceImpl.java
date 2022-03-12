package idv.hzm.app.service.impl;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import idv.hzm.app.mode.Coin;
import idv.hzm.app.mode.coindesk.BpiContent;
import idv.hzm.app.mode.coindesk.Coindesk;
import idv.hzm.app.mode.coindesk.CoindeskNewInfo;
import idv.hzm.app.mode.coindesk.TimeContent;
import idv.hzm.app.repository.CoinRepository;
import idv.hzm.app.service.CoindeskService;

@Service
public class CoindeskServiceImpl implements CoindeskService {
	
	@Autowired
	private CoinRepository coinRepository;

	@Override
	public ResponseEntity<CoindeskNewInfo> getCoindeskNewInfo() {
		Coindesk coindesk = this.resCoindesk();

		TimeContent timeContent = coindesk.getTime();

		String updated = dateTimeConvert(timeContent.getUpdated(), Locale.ENGLISH, "MMM dd, yyyy HH:mm:ss z",
				"yyyy/MM/dd HH:mm:ss");
		String updatedISO = dateTimeOffsetConvert(timeContent.getUpdatedISO(), "yyyy/MM/dd HH:mm:ss");
		String updateduk = dateTimeConvert(timeContent.getUpdateduk(), Locale.ENGLISH, "MMM dd, yyyy 'at' HH:mm z",
				"yyyy/MM/dd HH:mm:ss");

		TimeContent convertTimeContent = new TimeContent();
		convertTimeContent.setUpdated(updated);
		convertTimeContent.setUpdatedISO(updatedISO);
		convertTimeContent.setUpdateduk(updateduk);

		
		
		Map<String, BpiContent> bpiMap = coindesk.getBpi();
		Set<String> bpiKeySet = bpiMap.keySet();
		Map<String, Map<String, Object>> convertbpiMap = new HashMap<>();
		for(String key : bpiKeySet) {
			BpiContent bpiContent = bpiMap.get(key);
			
			String code = bpiContent.getCode();
			Coin coin = coinRepository.findById(code).get();
			
			Map<String, Object> coinContent = new HashMap<>();
			coinContent.put("code", code);
			coinContent.put("chCode", coin.getChCode());
			coinContent.put("rate", bpiContent.getRate());
			coinContent.put("rate_float", bpiContent.getRate_float());
			
			convertbpiMap.put(key, coinContent);
		}
		
		CoindeskNewInfo coindeskInfo = new CoindeskNewInfo();
		coindeskInfo.setTime(convertTimeContent);
		coindeskInfo.setBpi(convertbpiMap);
		return new ResponseEntity<CoindeskNewInfo>(coindeskInfo, HttpStatus.OK);
	}

	private String dateTimeGMTConvert(String dateTime, Locale language, String inPatern, String outPatern) {
		// Mar 12, 2022 at 02:00 GMT
		DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern(inPatern, Locale.ENGLISH);
		OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTime, inFormatter);
		DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern(outPatern);
		return offsetDateTime.format(outFormatter);
	}

	@Override
	public ResponseEntity<Coindesk> getCoindesk() {
		return new ResponseEntity<Coindesk>(this.resCoindesk(), HttpStatus.OK);
	}

	private String dateTimeConvert(String dateTime, Locale language, String inPatern, String outPatern) {
		DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(inPatern, language);
		LocalDateTime date = LocalDateTime.parse(dateTime, inputFormat);
		DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(outPatern);
		return date.format(outputFormat);
	}

	private String dateTimeOffsetConvert(String dateTime, String outPatern) {
		OffsetDateTime date = OffsetDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(outPatern);
		return date.format(outputFormat);
	}

	private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(1000);// 1秒
		return clientHttpRequestFactory;
	}

	private Coindesk resCoindesk() {

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		// Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

		// Note: here we are making this converter to process any kind of response,
		// not only application/*json, which is the default behaviour
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);

		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		restTemplate.setMessageConverters(messageConverters);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		ResponseEntity<Coindesk> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Coindesk.class);
		return responseEntity.getBody();
	}
}
