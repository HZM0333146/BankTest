package idv.hzm.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import idv.hzm.app.controller.CoinController;

@SpringBootTest
class BankTestApplicationTests {
	
	@Autowired
	private CoinController  coinController;

	@Test
	void contextLoads() {
		assertThat(coinController).isNotNull();
	}

}
