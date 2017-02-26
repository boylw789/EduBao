package model;

import java.util.List;

import bean.ApkCommentBean;

public interface IOnGetAppCommentListener {

	void getHttpCommentDataSuccess(List<ApkCommentBean> cList, int recordCount);

	void getHttpCommentDataFailed(String msg);

	void getHttpCommentDataNull();
}
