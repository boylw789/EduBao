package model;

import java.util.List;

import bean.ClassfiyBean;


public interface IOnGetClassfiyDataListener {

	void getClassfiyDataSuccess(List<ClassfiyBean> cList);

	void getClassfiyDataFailed(String msg);
}
