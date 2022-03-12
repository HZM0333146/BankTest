package idv.hzm.app.mode.coindesk;

import java.util.Map;

public class Coindesk {
	private TimeContent time;
	private String disclaimer;
	private String chartName;
	private Map<String, BpiContent> bpi;

	public void setTime(TimeContent time) {
		this.time = time;
	}
	public TimeContent getTime() {
		return time;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public Map<String, BpiContent> getBpi() {
		return bpi;
	}
	public void setBpi(Map<String, BpiContent> bpi) {
		this.bpi = bpi;
	}
}
