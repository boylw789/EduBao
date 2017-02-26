package bean;

import java.io.Serializable;

public class ApkInfoBean implements Serializable {

	private String id;
	private String name;
	private String icon;
	private String packageName;
	private String length;
	private String downloadCount;
	private String details;
	private String gridName;
	private String childName;
	private String downLoadPath;
	private String avgScore;

	public ApkInfoBean() {

	}

	public ApkInfoBean(String id, String name, String icon, String packageName,
			String length, String downloadCount, String details,
			String gridName, String childName, String downLoadPath,String avgScore) {

		this.id = id;
		this.name = name;
		this.icon = icon;
		this.packageName = packageName;
		this.length = length;
		this.downLoadPath = downLoadPath;
		this.downloadCount = downloadCount;
		this.details = details;
		this.gridName = gridName;
		this.childName = childName;
		this.avgScore=avgScore;
	}

	public String getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(String avgScore) {
		this.avgScore = avgScore;
	}

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

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(String downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getGridName() {
		return gridName;
	}

	public void setGridName(String gridName) {
		this.gridName = gridName;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getDownLoadPath() {
		return downLoadPath;
	}

	public void setDownLoadPath(String downLoadPath) {
		this.downLoadPath = downLoadPath;
	}
}
