package model;


import bean.ApkDetailsBean;

public interface IOnGetAppDetailsListener {

	void getHttpApkDetailsDataSuccess(ApkDetailsBean apkDetailsBean);

	void getHttpApkDetailsDataFailed(String msg);
}
