package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.boylw789.edubao.R;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import adapter.ListAdapter;
import bean.ApkInfoBean;
import bean.SpaceItemDecoration;
import bean.Urls;
import presenter.ListDataPresenter;
import view.SearchEditText;
import viewinterface.IRecycleView;


public class SearchActivity extends BaseActivity implements OnRefreshListener,
        OnLoadMoreListener, IRecycleView {

    private SearchEditText sEdittext;
    private LinearLayout nLayout;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView sRecyclerView;

    private ListAdapter sAdapter;
    private List<ApkInfoBean> slist;
    private ListDataPresenter sPresenter;

    private int pageIndex = 1;

    private boolean mHasLoadedOnce;
    private String keyString;
    private static final String SEARCH_ADPTER_TYPE = "SEARCH_ADPTER_TYPE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findViews();
        init();
    }

    public void findViews() {

        sEdittext = (SearchEditText) findViewById(R.id.edittext_search_key);
        nLayout = (LinearLayout) findViewById(R.id.layout_search_null);
        sRecyclerView = (RecyclerView) findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.search_swipetoloadlayout);
    }

    public void init() {

        sAdapter = new ListAdapter(this, SEARCH_ADPTER_TYPE);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        sRecyclerView.setAdapter(sAdapter);
        sRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        sRecyclerView.setLayoutManager(layoutManager);
        sRecyclerView.addItemDecoration(new SpaceItemDecoration(16));
        sRecyclerView.addOnScrollListener(new OnScrollListener() {

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

        sPresenter = new ListDataPresenter(this);

        sEdittext.setOnEditorActionListener(new OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    keyString = sEdittext.getText().toString();

                    mHasLoadedOnce = false;
                    sPresenter.bindData(Urls.searchType, pageIndex,
                            mHasLoadedOnce, keyString);
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public void onLoadMore() {

        sPresenter.bindData(Urls.searchType, pageIndex, mHasLoadedOnce,
                keyString);
    }

    @Override
    public void onRefresh() {

        pageIndex = 1;
        sPresenter.bindData(Urls.searchType, pageIndex, mHasLoadedOnce,
                keyString);
    }

    @Override
    public void showLoading() {

        showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {

        hideLoadingDialog();
    }

    @Override
    public void bindData(List<ApkInfoBean> list, int recordCount) {

        mHasLoadedOnce = true;
        swipeToLoadLayout.setVisibility(View.VISIBLE);
        nLayout.setVisibility(View.GONE);
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
        if (slist == null) {

            slist = new ArrayList<ApkInfoBean>();
        }

        if (pageIndex > (recordCount / Urls.pageSize + 1)) {

            list.clear();
        }
        slist.addAll(list);
        if (pageIndex == 1) {

            sAdapter.SetData(list, keyString);
        } else {

            if (list == null || list.size() == 0) {

                Toast.makeText(SearchActivity.this, "没有匹配软件！",
                        Toast.LENGTH_SHORT).show();
            } else {

                sAdapter.SetData(slist, keyString);
            }
        }
        pageIndex++;

    }

    @Override
    public void bindDataFailed(String Msg) {

        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
        Toast.makeText(SearchActivity.this, Msg, Toast.LENGTH_SHORT).show();
        swipeToLoadLayout.setVisibility(View.GONE);
        nLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }

    @Override
    public void bindNullData() {

        Toast.makeText(SearchActivity.this, "Sorry,未找到软件！", Toast.LENGTH_SHORT)
                .show();
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setVisibility(View.GONE);
        nLayout.setVisibility(View.VISIBLE);
    }
}
