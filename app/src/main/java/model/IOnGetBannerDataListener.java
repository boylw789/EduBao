package model;

import java.util.List;

import bean.BannerImagesBean;


public interface IOnGetBannerDataListener {

	void getBannerDataSuccess(List<BannerImagesBean> bList);

	void getBannerDataFailed(String msg);
}
