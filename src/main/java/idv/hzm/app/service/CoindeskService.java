package idv.hzm.app.service;

import org.springframework.http.ResponseEntity;

import idv.hzm.app.mode.coindesk.Coindesk;
import idv.hzm.app.mode.coindesk.CoindeskNewInfo;

public interface CoindeskService {
	public ResponseEntity<CoindeskNewInfo> getCoindeskNewInfo();

	public ResponseEntity<Coindesk> getCoindesk();
}
