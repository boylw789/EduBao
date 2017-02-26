package bean;

import java.io.Serializable;

/**
 * 
 * �����������
 *
 */
public class ApkDetailsTestDataBean implements Serializable {

	private String installNormal;
	private String runingNormal;
	private String free;
	private String onlyUse;
	private String register;
	private String safeScan;
	private String advert;

	public String getInstallNormal() {
		return installNormal;
	}

	public void setInstallNormal(String installNormal) {
		this.installNormal = installNormal;
	}

	public String getRuningNormal() {
		return runingNormal;
	}

	public void setRuningNormal(String runingNormal) {
		this.runingNormal = runingNormal;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getOnlyUse() {
		return onlyUse;
	}

	public void setOnlyUse(String onlyUse) {
		this.onlyUse = onlyUse;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getSafeScan() {
		return safeScan;
	}

	public void setSafeScan(String safeScan) {
		this.safeScan = safeScan;
	}

	public String getAdvert() {
		return advert;
	}

	public void setAdvert(String advert) {
		this.advert = advert;
	}

}
