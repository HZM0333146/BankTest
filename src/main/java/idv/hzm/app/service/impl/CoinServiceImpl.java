package idv.hzm.app.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import idv.hzm.app.dao.Coin;
import idv.hzm.app.service.CoinService;

@Service
public class CoinServiceImpl implements CoinService {

	@Override
	public ResponseEntity<String> getAllCoin() {
		return null;
	}

	@Override
	public ResponseEntity<String> getCoin(Integer id) {
		return null;
	}

	@Override
	public ResponseEntity<String> addCoin(Coin coin) {
		return null;
	}

	@Override
	public ResponseEntity<String> upDateCoin(Integer id, Coin coin) {
		return null;
	}

	@Override
	public ResponseEntity<String> delCoin(Integer id) {
		return null;
	}
	
}
