package bean;

import java.util.List;


public class ClassfiyBean {

	private String gridID;
	private String gridName;
	private String gridAppNum;
	private List<ChildBean> childData;

	public String getGridID() {
		return gridID;
	}

	public void setGridID(String gridID) {
		this.gridID = gridID;
	}

	public String getGridName() {
		return gridName;
	}

	public void setGridName(String gridName) {
		this.gridName = gridName;
	}

	public String getGridAppNum() {
		return gridAppNum;
	}

	public void setGridAppNum(String gridAppNum) {
		this.gridAppNum = gridAppNum;
	}

	public List<ChildBean> getChildData() {
		return childData;
	}

	public void setChildData(List<ChildBean> childData) {
		this.childData = childData;
	}

}
