package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.boylw789.edubao.R;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import activity.SearchActivity;
import adapter.ListAdapter;
import bean.ApkInfoBean;
import bean.SpaceItemDecoration;
import bean.Urls;
import presenter.ListDataPresenter;
import viewinterface.IRecycleView;

public class RecommendFragment extends BaseFragment implements IRecycleView,
		OnRefreshListener, OnLoadMoreListener {

	private View reView;
	private Context context;
	private boolean isPrepared;
	private boolean mHasLoadedOnce;

	private SwipeToLoadLayout rSwipeToLoadLayout;
	private RecyclerView reRecyclerView;

	private ListAdapter rAdapter;
	private List<ApkInfoBean> rlist;
	private ListDataPresenter rPresenter;
	private int pageIndex = 1;

	private TextView sTextView;
	private static final String RANK_ADPTER_TYPE = "RANK_ADPTER_TYPE";

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		if (reView == null) {

			context = getActivity();
			reView = inflater.inflate(R.layout.fragment_recommend, null, false);
			findViews();
			init();
			isPrepared = true;
			lazyLoad();
		}
		ViewGroup parent = (ViewGroup) reView.getParent();
		if (parent != null) {

			parent.removeView(reView);
		}
		return reView;
	}

	public void findViews() {

		rSwipeToLoadLayout = (SwipeToLoadLayout) reView
				.findViewById(R.id.recommend_swipetoloadlayout);
		reRecyclerView = (RecyclerView) reView.findViewById(R.id.swipe_target);

		reRecyclerView.setHasFixedSize(true);

		LinearLayoutManager layoutManager = new LinearLayoutManager(context);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		reRecyclerView.setLayoutManager(layoutManager);
		reRecyclerView.addItemDecoration(new SpaceItemDecoration(16));
		sTextView = (TextView) reView.findViewById(R.id.textview_search_rank);
		sTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent sIntent = new Intent(context, SearchActivity.class);
				startActivity(sIntent);
			}
		});
	}

	public void init() {

		rAdapter = new ListAdapter(context, RANK_ADPTER_TYPE);
		reRecyclerView.setAdapter(rAdapter);
		rSwipeToLoadLayout.setOnRefreshListener(this);
		rSwipeToLoadLayout.setOnLoadMoreListener(this);
		reRecyclerView.addOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView,
					int newState) {

				if (newState == RecyclerView.SCROLL_STATE_IDLE) {

					if (!ViewCompat.canScrollVertically(recyclerView, 1)) {

						rSwipeToLoadLayout.setLoadingMore(true);
					}
				}
			}
		});
		rPresenter = new ListDataPresenter(this);
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		if (!isPrepared || !isVisible || mHasLoadedOnce) {

			return;
		}

		rPresenter.bindData(Urls.rankType, pageIndex, mHasLoadedOnce, null);
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
	public void bindData(List<ApkInfoBean> list, int recordCount) {

		mHasLoadedOnce = true;
		rSwipeToLoadLayout.setLoadingMore(false);
		rSwipeToLoadLayout.setRefreshing(false);
		if (rlist == null) {

			rlist = new ArrayList<ApkInfoBean>();
		}

		if (pageIndex > (recordCount / Urls.pageSize + 1)) {

			list.clear();
		}
		rlist.addAll(list);
		if (pageIndex == 1) {

			rAdapter.SetData(list, null);
		} else {

			if (list == null || list.size() == 0) {

				Toast.makeText(context, "error!", Toast.LENGTH_SHORT).show();
			} else {

				rAdapter.SetData(rlist, null);
			}
		}
		pageIndex++;
	}

	@Override
	public void bindDataFailed(String Msg) {

		rSwipeToLoadLayout.setLoadingMore(false);
		rSwipeToLoadLayout.setRefreshing(false);
		Toast.makeText(context, Msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onLoadMore() {

		rPresenter.bindData(Urls.rankType, pageIndex, mHasLoadedOnce, null);
	}

	@Override
	public void onRefresh() {

		pageIndex = 1;
		rPresenter.bindData(Urls.rankType, pageIndex, mHasLoadedOnce, null);
	}

	@Override
	public void onResume() {

		super.onResume();
		rAdapter.notifyDataSetChanged();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		OkHttpUtils.getInstance().cancelTag(this);
	}

	@Override
	public void bindNullData() {

	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}
