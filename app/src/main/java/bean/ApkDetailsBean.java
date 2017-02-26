package bean;

import java.io.Serializable;
import java.util.List;

public class ApkDetailsBean implements Serializable {

	private String id;
	private String name;
	private String icon;
	private String packageName;
	private String avgScore;
	private String length;
	private String downLoadPath;
	private String devepter;
	private String function;
	private ApkDetailsImagesBean images;
	private ApkDetailsTestDataBean testData;
	private List<ApkDetalisSameAppBean> sameAPP;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(String avgScore) {
		this.avgScore = avgScore;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDownLoadPath() {
		return downLoadPath;
	}

	public void setDownLoadPath(String downLoadPath) {
		this.downLoadPath = downLoadPath;
	}

	public String getDevepter() {
		return devepter;
	}

	public void setDevepter(String devepter) {
		this.devepter = devepter;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public ApkDetailsImagesBean getImages() {
		return images;
	}

	public void setImages(ApkDetailsImagesBean images) {
		this.images = images;
	}

	public ApkDetailsTestDataBean getTestData() {
		return testData;
	}

	public void setTestData(ApkDetailsTestDataBean testData) {
		this.testData = testData;
	}

	public List<ApkDetalisSameAppBean> getSameAPP() {
		return sameAPP;
	}

	public void setSameAPP(List<ApkDetalisSameAppBean> sameAPP) {
		this.sameAPP = sameAPP;
	}

}
