package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

import com.example.boylw789.edubao.R;

import java.util.List;

import adapter.ClassfiyAdapter;
import bean.ClassfiyBean;
import bean.Urls;
import presenter.ClassfiyPresenter;
import viewinterface.IClassfiyview;


public class ClassifyFragment extends BaseFragment implements IClassfiyview {

	private View cView;
	private Context context;
	private boolean isPrepared;
	private boolean mHasLoadedOnce;

	private ClassfiyPresenter cPresenter;
	private ExpandableListView mExpandableListView;
	private ClassfiyAdapter cAdapter;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		if (cView == null) {

			context = getActivity();
			cView = inflater.inflate(R.layout.fragment_classify, null, true);
			init();
			isPrepared = true;
			lazyLoad();
		}
		ViewGroup parent = (ViewGroup) cView.getParent();
		if (parent != null) {

			parent.removeView(cView);
		}
		return cView;
	}

	public void init() {

		mExpandableListView = (ExpandableListView) cView
				.findViewById(R.id.expandablelistview_classfiy);
		cAdapter = new ClassfiyAdapter(context);
		mExpandableListView.setAdapter(cAdapter);

		mExpandableListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1,
					int arg2, long arg3) {

				return true;
			}
		});
		cPresenter = new ClassfiyPresenter(this);
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		if (!isPrepared || !isVisible || mHasLoadedOnce) {

			return;
		}

		cPresenter.loadClassfiyData(Urls.classType, mHasLoadedOnce);
	}

	@Override
	public void showLoading() {

		showLoadingDialog(context);
	}

	@Override
	public void hideLoading() {

		hideLoadingDialog();
	}

	@Override
	public void bindClassfiyDataSuccessed(List<ClassfiyBean> cList) {

		mHasLoadedOnce = true;
		cAdapter.setClassfiyData(cList);
		for (int i = 0; i < cAdapter.getGroupCount(); i++) {

			mExpandableListView.expandGroup(i);
		}
	}

	@Override
	public void bindClassfiyDataFailed(String msg) {

		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
