package idv.hzm.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import idv.hzm.app.mode.Coin;
import idv.hzm.app.repository.CoinRepository;
import idv.hzm.app.service.CoinService;

@Service
public class CoinServiceImpl implements CoinService {
	
	@Autowired
	private CoinRepository coinRepository;

	@Override
	public ResponseEntity<List<Coin>> getAllCoin() {
		return new ResponseEntity<List<Coin>>(coinRepository.findAll(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Coin> getCoin(String code) {
		return new ResponseEntity<Coin>(coinRepository.findById(code).get(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> addCoin(Coin coin) {
		return new ResponseEntity<String>(coinRepository.save(coin).getCode(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> upDateCoin(String id, Coin coin) {
		return new ResponseEntity<String>(coinRepository.save(coin).getCode(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> delCoin(String code) {
		coinRepository.deleteById(code);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}

}
