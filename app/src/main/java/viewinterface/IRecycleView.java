package viewinterface;

import java.util.List;

import bean.ApkInfoBean;

public interface IRecycleView {

	void showLoading();

	void hideLoading();

	void bindData(List<ApkInfoBean> list, int recordCount);

	void bindDataFailed(String Msg);

	void bindNullData();
}
