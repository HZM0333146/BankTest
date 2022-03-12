package idv.hzm.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.hzm.app.mode.Coin;
import idv.hzm.app.mode.coindesk.Coindesk;
import idv.hzm.app.mode.coindesk.CoindeskNewInfo;
import idv.hzm.app.service.impl.CoinServiceImpl;
import idv.hzm.app.service.impl.CoindeskServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/banktest")
public class CoinController {

	@Autowired
	private CoinServiceImpl coinService;

	@GetMapping(value = "/coins", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "取得所有錢幣", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<List<Coin>> getAllCoin() {
		return this.coinService.getAllCoin();
	}

	@GetMapping("/coins/{code}")
	@ApiOperation(value = "取得一種錢幣", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<Coin> getCoin(@PathVariable("code") String code) {
		return this.coinService.getCoin(code);
	}

	@PostMapping("/coins")
	@ApiOperation(value = "新增一種錢幣", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<String> addCoin(@RequestBody Coin coin) {
		return this.coinService.addCoin(coin);
	}

	@PutMapping("/coins/{code}")
	@ApiOperation(value = "修改錢幣訊息", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<String> upDateCoin(@PathVariable("code") String code, @RequestBody Coin coin) {
		return this.coinService.upDateCoin(code,coin);
	}

	@DeleteMapping("/coins/{code}")
	@ApiOperation(value = "刪除一種錢幣訊息", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<Boolean> delCoin(@PathVariable("code") String code) {
		return this.coinService.delCoin(code);
	}
	
	@Autowired
	private CoindeskServiceImpl coindeskService;
	
	@GetMapping(value = "/coindesk")
	@ApiOperation(value = "coindesk api", notes = "呼叫coindesk api")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<Coindesk> getCoindesk() {
		return this.coindeskService.getCoindesk();
	}
	
	@GetMapping(value = "/coindesknewinfo")
	@ApiOperation(value = "coindesk api data change", notes = "呼叫coindesk api,轉換資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<CoindeskNewInfo> getCoindeskNewInfo() {
		return this.coindeskService.getCoindeskNewInfo();
	}

}
