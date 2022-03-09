package idv.hzm.app.dto;

import org.springframework.http.ResponseEntity;

import idv.hzm.app.dao.Coin;

public interface CoinDto {
	public ResponseEntity<String> getAllCoin();

	public ResponseEntity<String> getCoin(Integer id);

	public ResponseEntity<String> addCoin(Coin coin);

	public ResponseEntity<String> upDateCoin(Integer id, Coin coin);

	public ResponseEntity<String> delCoin(Integer id);
}
