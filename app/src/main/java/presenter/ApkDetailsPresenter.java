package presenter;


import bean.ApkDetailsBean;
import bean.Urls;
import model.ApkDetailsDataModel;
import model.IOnGetAppDetailsListener;
import viewinterface.IListView;

public class ApkDetailsPresenter implements IOnGetAppDetailsListener {

	private IListView iListView;
	private ApkDetailsDataModel apkDetailsDataModel;

	public ApkDetailsPresenter(IListView iListView) {

		this.iListView = iListView;
		this.apkDetailsDataModel = new ApkDetailsDataModel();

	}
	/**
	 * 
	 * @param type
	 * @param id
	 */
	public void loadApkDetailsData(int type, String id) {

		String urlString = Urls.getUlrString(type, 0, null) + "&id=" + id;
		iListView.showLoading();
		apkDetailsDataModel.loadAppDetailsData(urlString, this);
	}

	@Override
	public void getHttpApkDetailsDataSuccess(ApkDetailsBean apkDetailsBean) {

		iListView.hideLoading();
		iListView.bindAppDetailsDataSuccessed(apkDetailsBean);
	}

	@Override
	public void getHttpApkDetailsDataFailed(String msg) {

		iListView.hideLoading();
		iListView.bindAppDetailsDataFailed(msg);

	}

}
