package model;

import java.util.List;

import bean.ApkInfoBean;

public interface IOnGethttpDataListener {

	void getHttpDataSuccess(List<ApkInfoBean> list, int recordCount);

	void getHttpDataFailed(String Msg);

	void getHttpDataNull();
}
