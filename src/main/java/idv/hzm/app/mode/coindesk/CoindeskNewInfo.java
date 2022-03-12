package idv.hzm.app.mode.coindesk;

import java.util.Map;

public class CoindeskNewInfo {
	private TimeContent time;
	private Map<String, Map<String, Object>> bpi;

	public TimeContent getTime() {
		return time;
	}

	public void setTime(TimeContent time) {
		this.time = time;
	}

	public Map<String, Map<String, Object>> getBpi() {
		return bpi;
	}

	public void setBpi(Map<String, Map<String, Object>> bpi) {
		this.bpi = bpi;
	}
}
