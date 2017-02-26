package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boylw789.edubao.R;

import java.io.Serializable;
import java.util.List;

import bean.ApkCommentBean;


public class AppAdverFragment extends BaseFragment {

	private View aView;

	private Context context;

	private boolean isPrepared;

	private boolean mHasLoadedOnce;

	public static final String DETAILS = "details";

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		if (aView == null) {

			context = getActivity();
			aView = inflater.inflate(R.layout.fragment_appadver, null, true);
			// findViews();
			isPrepared = true;
			lazyLoad();
		}
		ViewGroup parent = (ViewGroup) aView.getParent();
		if (parent != null) {

			parent.removeView(aView);
		}
		return aView;
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}

	public static AppAdverFragment newInstance(List<ApkCommentBean> cList) {

		AppAdverFragment tabFragment = new AppAdverFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(DETAILS, (Serializable) cList);
		tabFragment.setArguments(bundle);
		return tabFragment;
	}

	@Override
	public String getTitle() {
		return "推荐";
	}
}
