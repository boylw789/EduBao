package presenter;

import java.util.List;

import bean.BannerImagesBean;
import bean.Urls;
import model.BannerDataModel;
import model.IOnGetBannerDataListener;
import viewinterface.ILoopViewPager;


public class BannerPresenter implements IOnGetBannerDataListener {

	private BannerDataModel bDataModel;
	private ILoopViewPager bViewPager;

	public BannerPresenter(ILoopViewPager bViewPager) {

		this.bViewPager = bViewPager;
		bDataModel = new BannerDataModel();
	}

	public void bindBannerData(int type, int pageIndex) {

		String urlString = Urls.getUlrString(type, pageIndex,null);
		bDataModel.loadBannerData(urlString, this);
	}

	@Override
	public void getBannerDataSuccess(List<BannerImagesBean> bList) {

		bViewPager.bindBanner(bList);
	}

	@Override
	public void getBannerDataFailed(String msg) {
		// TODO Auto-generated method stub

	}
}
