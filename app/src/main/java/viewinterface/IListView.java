package viewinterface;


import bean.ApkDetailsBean;

public interface IListView {

	void showLoading();

	void hideLoading();

	void bindAppDetailsDataSuccessed(ApkDetailsBean apkDetailsBean);

	void bindAppDetailsDataFailed(String msg);
}
