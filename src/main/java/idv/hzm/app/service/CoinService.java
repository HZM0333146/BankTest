package idv.hzm.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import idv.hzm.app.mode.Coin;

public interface CoinService {

	public ResponseEntity<List<Coin>> getAllCoin();

	public ResponseEntity<Coin> getCoin(String code);

	public ResponseEntity<String> addCoin(Coin coin);
		
	public ResponseEntity<String> upDateCoin(String code, Coin coin);

	public ResponseEntity<Boolean> delCoin(String code);
		
}