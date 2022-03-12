package idv.hzm.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import idv.hzm.app.mode.Coin;
import idv.hzm.app.mode.coindesk.Coindesk;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CoinConrollerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;

	private String getRootUrl() {
        return "http://localhost:" + port + "/banktest";
    }

	@Test
	public void getAllCoin() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/coins",
        HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
	}
	
	
	@Test
	public void getCoin() {
		String code = "USD";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Coin> response = restTemplate.exchange(getRootUrl() + "/coins/" + code,
		        HttpMethod.GET, entity, Coin.class);
		Coin coin = response.getBody();
		assertNotNull(coin);
		System.out.println(coin);
	}
	
	@Test
	public void addCoin() {
		Coin coin = new Coin();
		coin.setCode("USD");
		coin.setChCode("新臺幣");
		ResponseEntity<String> response = restTemplate.postForEntity(getRootUrl() + "/coins/",coin, String.class);
		assertNotNull(response);
		assertEquals(response.getBody(),"USD");
		System.out.println(response.getBody());
	}
	
	@Test
	public void upDateCoin() {
		String code = "USD";
		Coin coin = restTemplate.getForObject(getRootUrl() + "/coins/" + code, Coin.class);
		coin.setChCode("台幣");
        restTemplate.put(getRootUrl() + "/coins/" + code, coin);
        Coin updateCoin = restTemplate.getForObject(getRootUrl() + "/coins/" + code, Coin.class);
        assertNotNull(updateCoin);
	}
	
	@Test
	public void delCoin() {
		String code = "USD";
		Coin coin = restTemplate.getForObject(getRootUrl() + "/coins/" + code, Coin.class);
        assertNotNull(coin);
        restTemplate.delete(getRootUrl() + "/coins/" + code);
        try {
        	coin = restTemplate.getForObject(getRootUrl() + "/employees/" + code, Coin.class);
        } catch (final HttpClientErrorException e) {
             assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
	}
	
	@Test
	public void getCoindesk() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Coindesk> response = restTemplate.exchange(getRootUrl() + "/coindesk",
        HttpMethod.GET, entity, Coindesk.class);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
	}
	
	@Test
	public void getCoindeskNewInfo() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Coindesk> response = restTemplate.exchange(getRootUrl() + "/coindesk",
        HttpMethod.GET, entity, Coindesk.class);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
	}

}
