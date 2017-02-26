package viewinterface;

import java.util.List;

import bean.ClassfiyBean;


public interface IClassfiyview {

	void showLoading();

	void hideLoading();

	void bindClassfiyDataSuccessed(List<ClassfiyBean> cList);

	void bindClassfiyDataFailed(String msg);
}
