package presenter;

import java.util.List;

import bean.ClassfiyBean;
import bean.Urls;
import model.ClassfiyDataModel;
import model.IOnGetClassfiyDataListener;
import viewinterface.IClassfiyview;


public class ClassfiyPresenter implements IOnGetClassfiyDataListener {

	private IClassfiyview iClassfiyview;
	private ClassfiyDataModel classfiyDataModel;

	public ClassfiyPresenter(IClassfiyview iClassfiyview) {

		this.iClassfiyview = iClassfiyview;
		classfiyDataModel = new ClassfiyDataModel();
	}

	public void loadClassfiyData(int type, boolean mHasLoadedOnce) {

		String urlString = Urls.getUlrString(type, 0, null);

		if (mHasLoadedOnce == false) {

			iClassfiyview.showLoading();
		}
		classfiyDataModel.loadClassData(urlString, this);
	}

	@Override
	public void getClassfiyDataSuccess(List<ClassfiyBean> cList) {

		iClassfiyview.hideLoading();
		iClassfiyview.bindClassfiyDataSuccessed(cList);
	}

	@Override
	public void getClassfiyDataFailed(String msg) {

		iClassfiyview.hideLoading();
		iClassfiyview.bindClassfiyDataFailed(msg);
	}

}
