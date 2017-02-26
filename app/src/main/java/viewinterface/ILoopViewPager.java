package viewinterface;

import java.util.List;

import bean.BannerImagesBean;


public interface ILoopViewPager {

	void bindBanner(List<BannerImagesBean> bList);

	void bindBannerFailed(String msg);
}
