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

public class AppCommentFragment extends BaseFragment {

	private View cView;
	private Context context;
	private boolean isPrepared;
	private boolean mHasLoadedOnce;
	public static final String DETAILS = "details";
	private List<ApkCommentBean> cList;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle bundle = getArguments();
		if (bundle != null) {

			cList = (List<ApkCommentBean>) getArguments().getSerializable(
					DETAILS);
		}
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		if (cView == null) {

			context = getActivity();
			cView = inflater.inflate(R.layout.fragment_appcomment, null, true);
			// findViews();
			isPrepared = true;
			lazyLoad();
		}
		ViewGroup parent = (ViewGroup) cView.getParent();
		if (parent != null) {

			parent.removeView(cView);
		}
		return cView;
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param cList
	 * @return
	 */
	public static AppCommentFragment newInstance(List<ApkCommentBean> cList) {

		AppCommentFragment tabFragment = new AppCommentFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(DETAILS, (Serializable) cList);
		tabFragment.setArguments(bundle);
		return tabFragment;
	}

	@Override
	public String getTitle() {
		return "评论";
	}

}
