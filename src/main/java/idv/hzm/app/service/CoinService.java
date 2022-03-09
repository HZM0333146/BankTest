package idv.hzm.app.service;

import org.springframework.http.ResponseEntity;

import idv.hzm.app.dao.Coin;

public interface CoinService {

	public ResponseEntity<String> getAllCoin();

	public ResponseEntity<String> getCoin(Integer id);

	public ResponseEntity<String> addCoin(Coin coin);

	public ResponseEntity<String> upDateCoin(Integer id, Coin coin);

	public ResponseEntity<String> delCoin(Integer id);
	
}