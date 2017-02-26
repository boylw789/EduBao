package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
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
import viewinterface.IRecycleView;

public class ClassfiydDetailsActivity extends BaseActivity implements
        OnRefreshListener, OnLoadMoreListener, IRecycleView {

    private TextView tTextView;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView dRecyclerView;
    private ImageView bImageView;

    private ListAdapter dAdapter;
    private List<ApkInfoBean> dlist;
    private ListDataPresenter dPresenter;

    private boolean mHasLoadedOnce;
    private static final String NORMAL_ADPTER_TYPE = "NORMAL_ADPTER_TYPE";

    private String gradeId;
    private String classifyId;
    private String titleName;
    private String gridName;

    private int pageIndex = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classfiydetails);
        gradeId = getIntent().getStringExtra("gradeId");
        classifyId = getIntent().getStringExtra("classifyId");
        titleName = getIntent().getStringExtra("titleName");
        gridName = getIntent().getStringExtra("gridName");
        findViews();
        init();
        loadData();
    }

    public void findViews() {

        bImageView = (ImageView) findViewById(R.id.imageview_classfiydetails_back);
        bImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                ClassfiydDetailsActivity.this.finish();
            }
        });
        tTextView = (TextView) findViewById(R.id.textview_classfiydetails_titlename);
        dRecyclerView = (RecyclerView) findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.classfiydetails_swipetoloadlayout);
    }

    public void init() {

        dAdapter = new ListAdapter(this, NORMAL_ADPTER_TYPE);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        dRecyclerView.setAdapter(dAdapter);
        dRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dRecyclerView.setLayoutManager(layoutManager);
        dRecyclerView.addItemDecoration(new SpaceItemDecoration(16));
        dRecyclerView.addOnScrollListener(new OnScrollListener() {

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

        dPresenter = new ListDataPresenter(this);
    }


    public void loadData() {

        tTextView.setText(gridName + "--" + titleName);
        dPresenter.bindData(Urls.getbyclaafiyType, pageIndex, mHasLoadedOnce,
                gradeId, classifyId);
    }

    @Override
    public void onRefresh() {

        pageIndex = 1;
        dPresenter.bindData(Urls.getbyclaafiyType, pageIndex, mHasLoadedOnce,
                gradeId, classifyId);
    }

    @Override
    public void onLoadMore() {

        dPresenter.bindData(Urls.getbyclaafiyType, pageIndex, mHasLoadedOnce,
                gradeId, classifyId);
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
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
        if (dlist == null) {

            dlist = new ArrayList<ApkInfoBean>();
        }

        if (pageIndex > (recordCount / Urls.pageSize + 1)) {

            list.clear();
        }
        dlist.addAll(list);
        if (pageIndex == 1) {

            dAdapter.SetData(list, null);
        } else {

            if (list == null || list.size() == 0) {

                Toast.makeText(ClassfiydDetailsActivity.this, "加载完成！",
                        Toast.LENGTH_SHORT).show();
            } else {

                dAdapter.SetData(dlist, null);
            }
        }
        pageIndex++;

    }

    @Override
    public void bindDataFailed(String Msg) {

        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
        Toast.makeText(ClassfiydDetailsActivity.this, Msg, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void bindNullData() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }

}
