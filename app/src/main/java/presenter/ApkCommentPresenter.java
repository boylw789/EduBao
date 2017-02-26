package presenter;

import java.util.List;

import bean.ApkCommentBean;
import bean.Urls;
import model.ApkCommentDataModel;
import model.IOnGetAppCommentListener;
import viewinterface.ICRecycleView;


public class ApkCommentPresenter implements IOnGetAppCommentListener {

	private ICRecycleView iRecycleView;
	private ApkCommentDataModel apkCommentDataModel;

	public ApkCommentPresenter(ICRecycleView iRecycleView) {

		this.iRecycleView = iRecycleView;
		this.apkCommentDataModel = new ApkCommentDataModel();
	}

	/**
	 * 
	 * @param type
	 * @param pageIndex
	 * @param id
	 */
	public void loadApkCommentData(int type, int pageIndex, String id) {

		String urlString = Urls.getUlrString(type, pageIndex, null) + "&id="
				+ id;
		apkCommentDataModel.loadAppCommentData(urlString, this);
	}

	@Override
	public void getHttpCommentDataFailed(String msg) {

		iRecycleView.bindDataFailed(msg);
	}

	@Override
	public void getHttpCommentDataNull() {

		iRecycleView.bindNullData();
	}

	@Override
	public void getHttpCommentDataSuccess(List<ApkCommentBean> cList,
			int recordCount) {

		iRecycleView.bindData(cList, recordCount);
	}
}
