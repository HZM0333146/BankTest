package idv.hzm.app.mode;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COIN_NAME")
public class Coin {
	@Id
	private String code;
	private String chCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getChCode() {
		return chCode;
	}

	public void setChCode(String chCode) {
		this.chCode = chCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chCode, code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coin other = (Coin) obj;
		return Objects.equals(chCode, other.chCode) && Objects.equals(code, other.code);
	}

	@Override
	public String toString() {
		return "Coin [code=" + code + ", chCode=" + chCode + "]";
	}
	
}
