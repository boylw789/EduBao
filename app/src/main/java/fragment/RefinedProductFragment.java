package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.boylw789.edubao.R;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import adapter.ListHeadAdapter;
import bean.ApkInfoBean;
import bean.BannerImagesBean;
import bean.SpaceItemDecoration;
import bean.Urls;
import presenter.BannerPresenter;
import presenter.ListDataPresenter;
import viewinterface.ILoopViewPager;
import viewinterface.IRecycleView;

public class RefinedProductFragment extends BaseFragment implements
        IRecycleView, OnRefreshListener, OnLoadMoreListener, ILoopViewPager {

    private View rView;
    private Context context;
    private boolean isPrepared;
    private boolean mHasLoadedOnce;

    private RecyclerView recyclerView;
    private ListHeadAdapter mAdapter;
    private List<ApkInfoBean> mlist;
    private ListDataPresenter presenter;

    private SwipeToLoadLayout swipeToLoadLayout;
    private int pageIndex = 1;
    private BannerPresenter bPresenter;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rView == null) {

            context = getActivity();
            rView = inflater.inflate(R.layout.fragment_refineproduct, null,
                    false);
            findViews();
            init();
            isPrepared = true;
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) rView.getParent();
        if (parent != null) {

            parent.removeView(rView);
        }
        return rView;
    }

    public void findViews() {

        recyclerView = (RecyclerView) rView.findViewById(R.id.swipe_target);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(16));
        swipeToLoadLayout = (SwipeToLoadLayout) rView
                .findViewById(R.id.refineproduct_swipetoloadlayout);
    }

    public void init() {

        mAdapter = new ListHeadAdapter(context);
        recyclerView.setAdapter(mAdapter);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        recyclerView.addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {

                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
        presenter = new ListDataPresenter(this);
        bPresenter = new BannerPresenter(this);
    }

    @Override
    protected void lazyLoad() {

        if (!isPrepared || !isVisible || mHasLoadedOnce) {

            return;
        }

        presenter.bindData(Urls.extractType, pageIndex, mHasLoadedOnce, null);
        bPresenter.bindBannerData(Urls.bannerType, pageIndex);
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
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
        if (mlist == null) {

            mlist = new ArrayList<ApkInfoBean>();
        }
        if (pageIndex > (recordCount / Urls.pageSize + 1)) {

            list.clear();
        }
        mlist.addAll(list);
        if (pageIndex == 1) {

            mAdapter.setList(list);
        } else {

            if (list == null || list.size() == 0) {

                Toast.makeText(context, "加载完成！", Toast.LENGTH_SHORT).show();
            } else {

                mAdapter.setList(mlist);
            }
        }
        pageIndex++;
    }

    @Override
    public void bindDataFailed(String Msg) {

        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
        Toast.makeText(context, Msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }

    @Override
    public void onResume() {

        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {

        presenter.bindData(Urls.extractType, pageIndex, mHasLoadedOnce, null);

    }

    @Override
    public void onRefresh() {

        pageIndex = 1;
        presenter.bindData(Urls.extractType, pageIndex, mHasLoadedOnce, null);
    }

    @Override
    public void bindBanner(List<BannerImagesBean> bList) {

        if (bList != null && bList.size() > 0) {

            mAdapter.setBanner(bList);
        }
    }

    @Override
    public void bindBannerFailed(String msg) {
        // TODO Auto-generated method stub

    }

    @Override
    public void bindNullData() {
        // TODO Auto-generated method stub

    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return null;
    }
}
