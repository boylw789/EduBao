package viewinterface;

import java.util.List;

import bean.ApkCommentBean;


public interface ICRecycleView {

	void bindData(List<ApkCommentBean> list, int recordCount);

	void bindDataFailed(String Msg);

	void bindNullData();
}
