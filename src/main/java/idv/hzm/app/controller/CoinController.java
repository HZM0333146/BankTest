package idv.hzm.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import idv.hzm.app.dao.Coin;
import idv.hzm.app.service.impl.CoinServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/banktest/coin")
public class CoinController {
	
	@GetMapping(value = "/coindesk")
	@ApiOperation(value = "取得所有手機資訊", notes = "取得所有手機資訊")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<String> getTest() {
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@Autowired
	private CoinServiceImpl coinService;

	@GetMapping(value = "/coin", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "取得所有錢幣", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<String> getAllCoin() {
		return new ResponseEntity<String>("成功", HttpStatus.OK);
	}

	@GetMapping("/coin/{id}")
	@ApiOperation(value = "取得一種錢幣", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<String> getCoin(@PathVariable("id") Integer id) {
		return new ResponseEntity<String>("成功", HttpStatus.OK);
	}

	@PostMapping("/coin")
	@ApiOperation(value = "新增一種錢幣", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<String> addCoin(@RequestBody Coin coin) {
		return new ResponseEntity<String>("成功", HttpStatus.OK);
	}

	@PutMapping("/coin/{id}")
	@ApiOperation(value = "修改錢幣訊息", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<String> upDateCoin(@PathVariable("id") Integer id, @RequestBody Coin coin) {
		return new ResponseEntity<String>("成功", HttpStatus.OK);
	}

	@DeleteMapping("/coin/{id}")
	@ApiOperation(value = "刪除一種錢幣訊息", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功", response = String.class)})
	public ResponseEntity<String> delCoin(@PathVariable("id") Integer id) {
		return new ResponseEntity<String>("成功", HttpStatus.OK);
	}

}
