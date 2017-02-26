package presenter;

import java.util.List;

import bean.ApkInfoBean;
import bean.Urls;
import model.IOnGethttpDataListener;
import model.ListDataModel;
import viewinterface.IRecycleView;


public class ListDataPresenter implements IOnGethttpDataListener {

	private ListDataModel mDataModel;
	private IRecycleView mIRecycleView;

	public ListDataPresenter(IRecycleView mIRecycleView) {

		this.mIRecycleView = mIRecycleView;
		mDataModel = new ListDataModel();
	}

	public void bindData(int type, int pageIndex, boolean mHasLoadedOnce,
			String keyString) {

		String urlString = Urls.getUlrString(type, pageIndex, keyString);
		// ��һ�ν������ʾ���ضԻ���
		if (mHasLoadedOnce == false) {

			mIRecycleView.showLoading();
		}
		mDataModel.loadData(urlString, this);
	}

	/**
	 * 
	 * @param type
	 * @param pageIndex
	 * @param mHasLoadedOnce
	 * @param gradeId
	 * @param classifyId
	 */
	public void bindData(int type, int pageIndex, boolean mHasLoadedOnce,
			String gradeId, String classifyId) {

		String urlString = Urls.getUlrString(type, pageIndex, null)
				+ "&gradeId=" + gradeId + "&classifyId=" + classifyId;

		if (mHasLoadedOnce == false) {

			mIRecycleView.showLoading();
		}
		mDataModel.loadData(urlString, this);
	}

	@Override
	public void getHttpDataSuccess(List<ApkInfoBean> list, int recordCount) {

		mIRecycleView.hideLoading();
		mIRecycleView.bindData(list, recordCount);
	}

	@Override
	public void getHttpDataFailed(String Msg) {

		mIRecycleView.hideLoading();
		mIRecycleView.bindDataFailed(Msg);
	}

	@Override
	public void getHttpDataNull() {

		mIRecycleView.hideLoading();
		mIRecycleView.bindNullData();
	}
}
