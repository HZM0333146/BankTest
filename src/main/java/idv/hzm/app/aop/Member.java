package idv.hzm.app.aop;

public class Member {
	private String type;
	private String device;
	private String memberId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Member [type=" + type + ", device=" + device + ", memberId=" + memberId + "]";
	}

}
