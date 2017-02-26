package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.boylw789.edubao.R;

import java.util.ArrayList;
import java.util.List;

import adapter.FragmentsViewPagerAdapter;
import bean.ApkCommentBean;
import bean.ApkDetailsBean;
import bean.Urls;
import fragment.AppAdverFragment;
import fragment.AppCommentFragment;
import fragment.AppDetailsFragment;
import fragment.BaseFragment;
import presenter.ApkCommentPresenter;
import presenter.ApkDetailsPresenter;
import view.PagerSlidingTabStrip;
import view.RatingBar;
import view.StickyNavLayout;
import viewinterface.ICRecycleView;
import viewinterface.IListView;

/**
 * 软件详情 （数据未在同一接口，不能传输数据，评论和推荐界面未能实现）
 */
public class AppDetailsActivity extends BaseActivity implements IListView,
        ICRecycleView {

    private ApkDetailsPresenter aPresenter;
    private ApkCommentPresenter cPresenter;
    private String id;
    private int pageIndex = 1;

    private ImageView bImageView;
    private ViewPager viewPager;
    private PagerSlidingTabStrip indicatorView;
    private StickyNavLayout stickyNavLayout;
    private boolean lastIsTopHidden;// 记录上次是否悬浮

    private ArrayList<BaseFragment> listFragments = new ArrayList<>();
    private List<ApkCommentBean> cList = new ArrayList<ApkCommentBean>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appdetails);
        id = getIntent().getStringExtra("id");
        findViews();
        init();
    }

    /**
     * 控件初始化
     */
    public void findViews() {

        bImageView = (ImageView) findViewById(R.id.imageview_appdetails_back);
        viewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        indicatorView = (PagerSlidingTabStrip) findViewById(R.id.id_stickynavlayout_indicator);
        bImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                finish();
            }
        });

        stickyNavLayout = (StickyNavLayout) findViewById(R.id.id_stick);
        stickyNavLayout
                .setOnStickStateChangeListener(new StickyNavLayout.onStickStateChangeListener() {

                    @Override
                    public void scrollPercent(float percent) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void isStick(boolean isStick) {

                        if (lastIsTopHidden != isStick) {

                            lastIsTopHidden = isStick;
                        }
                    }
                });
    }

    /**
     * 网络请求
     */
    public void init() {

        aPresenter = new ApkDetailsPresenter(this);
        aPresenter.loadApkDetailsData(Urls.getappdetailsType, id);

        cPresenter = new ApkCommentPresenter(this);
        cPresenter.loadApkCommentData(Urls.getappcommentType, pageIndex, id);
    }

    /**
     * 软件简介
     *
     * @param aDetailsBean
     */
    public void initAppDetails(ApkDetailsBean aDetailsBean) {

        listFragments.add(AppDetailsFragment.newInstance(aDetailsBean));
        listFragments.add(AppCommentFragment.newInstance(cList));
        listFragments.add(AppAdverFragment.newInstance(cList));

        FragmentManager fManager = this.getSupportFragmentManager();
        FragmentsViewPagerAdapter adapter = new FragmentsViewPagerAdapter(
                fManager, listFragments);
        viewPager.setAdapter(adapter);
        indicatorView.setViewPager(viewPager);
        indicatorView.setOnPageChangeListener(mPageChangeListener);
        viewPager.setOffscreenPageLimit(0);
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };

    @Override
    public void showLoading() {

        showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {

        hideLoadingDialog();
    }

    @Override
    public void bindAppDetailsDataSuccessed(ApkDetailsBean apkDetailsBean) {

        initHeadData(apkDetailsBean);
        initAppDetails(apkDetailsBean);
    }

    @Override
    public void bindAppDetailsDataFailed(String msg) {

        Toast.makeText(AppDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 绑定头部数据
     *
     * @param aBean
     */
    public void initHeadData(ApkDetailsBean aBean) {

        ImageView icon = (ImageView) findViewById(R.id.imageview_appdetails_icon);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingbar_appdetails_score);
        TextView lengthTextView = (TextView) findViewById(R.id.textview_appdetails_applength);
        TextView nameTextView = (TextView) findViewById(R.id.textview_appdetails_appname);
        TextView countTextView = (TextView) findViewById(R.id.textview_appdetails_appcount);

        Glide.with(this).load(aBean.getIcon()).error(R.mipmap.ic_launcher)
                .into(icon);
        nameTextView.setText(aBean.getName());
        ratingBar.setStar(Float.parseFloat(aBean.getAvgScore()));
        lengthTextView.setText(aBean.getLength());
        // countTextView.setText(aBean.get)
    }

    @Override
    public void bindData(List<ApkCommentBean> list, int recordCount) {

    }

    @Override
    public void bindDataFailed(String Msg) {
        // TODO Auto-generated method stub

    }

    @Override
    public void bindNullData() {
        // TODO Auto-generated method stub

    }
}