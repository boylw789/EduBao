package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.boylw789.edubao.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.AppdetailsImgAdapter;
import adapter.SameAppGridViewAdapter;
import bean.ApkDetailsBean;
import bean.ApkDetailsImagesBean;
import bean.ApkDetailsTestDataBean;
import bean.ApkDetalisSameAppBean;
import expandablelayout.ExpandableLinearLayout;
import view.NoScrollGridView;

public class AppDetailsFragment extends BaseFragment implements OnClickListener {

    private View dView;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;

    public static final String DETAILS = "details";
    private ApkDetailsBean apkDetailsBean;

    private TextView upTextView;
    private ExpandableLinearLayout eLayout;
    private boolean isexpand;

    private boolean mIsShortDescription = true;
    private boolean isInit = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {

            apkDetailsBean = (ApkDetailsBean) getArguments().getSerializable(
                    DETAILS);
        }
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (dView == null) {

            context = getActivity();
            dView = inflater.inflate(R.layout.fragment_appdetails, null, true);
            findViews();
            setTestData();
            setImgData();
            setFunctionData();
            setSameAppData();
            isPrepared = true;
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) dView.getParent();
        if (parent != null) {

            parent.removeView(dView);
        }
        return dView;
    }

    public void findViews() {

        LinearLayout sLyout = (LinearLayout) dView
                .findViewById(R.id.layout_appdetails_fragment_spread);
        sLyout.setOnClickListener(this);

        eLayout = (ExpandableLinearLayout) dView
                .findViewById(R.id.layout_appdetails_fragment_container);
        eLayout.collapse();// 默认收起
        upTextView = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_spread);
    }

    @Override
    protected void lazyLoad() {

        // setTestData();
    }

    /**
     * 绑定测试数据
     */
    public void setTestData() {

        ApkDetailsTestDataBean tBean = apkDetailsBean.getTestData();

        TextView freeTextView = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_free);
        TextView safeTextView = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_safe);
        TextView adverTextView = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_adver);

        // 是否免费
        freeTextView.setText(tBean.getFree());
        if (tBean.getFree().equals("免费")) {

            freeTextView.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_ok, 0, 0, 0);
        } else {

            freeTextView.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_bad, 0, 0, 0);
        }

        // 是否安全
        safeTextView.setText(tBean.getSafeScan());
        if (tBean.getSafeScan().equals("安全")) {

            safeTextView.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_ok, 0, 0, 0);
        } else {

            safeTextView.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_bad, 0, 0, 0);
        }

        // 是否有广告
        adverTextView.setText(tBean.getAdvert());
        if (tBean.getAdvert().equals("有广告")) {

            adverTextView.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_bad, 0, 0, 0);
        } else {

            adverTextView.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_ok, 0, 0, 0);
        }

        // 是否是线下安装
        TextView installNormal = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_installnormal_img);
        TextView iTextView = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_installnormal_text);
        iTextView.setText(tBean.getInstallNormal());
        if (tBean.getInstallNormal().equals("否")) {

            installNormal.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_bad, 0, 0, 0);
        } else {

            installNormal.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_ok, 0, 0, 0);
        }

        // 是否需要联网
        TextView runingNormal = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_running_img);
        TextView rTextView = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_running_text);
        rTextView.setText(tBean.getRuningNormal());
        if (tBean.getRuningNormal().equals("否")) {

            runingNormal.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_ok, 0, 0, 0);
        } else {

            runingNormal.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_bad, 0, 0, 0);
        }

        // 是否可离线运行
        TextView onlyUse = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_onlyuse_img);
        TextView oTextView = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_onlyuse_text);
        oTextView.setText(tBean.getOnlyUse());
        if (tBean.getOnlyUse().equals("可离线")) {

            onlyUse.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.test_ok,
                    0, 0, 0);
        } else {

            onlyUse.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_bad, 0, 0, 0);
        }

        // 是否需要注册
        TextView register = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_register_img);
        TextView rTextView2 = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_register_text);
        rTextView2.setText(tBean.getRegister());
        if (tBean.getRegister().equals("需注册")) {

            register.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_bad, 0, 0, 0);
        } else {

            register.setCompoundDrawablesWithIntrinsicBounds(
                    R.mipmap.test_ok, 0, 0, 0);
        }
    }

    /**
     * 绑定图片介绍
     */
    public void setImgData() {

        RecyclerView mRecyclerView = (RecyclerView) dView
                .findViewById(R.id.recycleview_appdetails_fragment_img);

        mRecyclerView.setHasFixedSize(true);
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        ApkDetailsImagesBean imgBean = apkDetailsBean.getImages();
        List<String> imags = new ArrayList<String>();
        imags.add(imgBean.getAI_FirstScreenshot());
        imags.add(imgBean.getAI_SecondScreenshot());
        imags.add(imgBean.getAI_ThirdScreenshot());
        imags.add(imgBean.getAI_FifthScreenshot());

        AppdetailsImgAdapter imgAdapter = new AppdetailsImgAdapter(context,
                imags);
        mRecyclerView.setAdapter(imgAdapter);
    }

    /**
     * 软件介绍
     */
    public void setFunctionData() {

        initProductInfo(apkDetailsBean.getFunction());
    }

    /**
     * 计算描述信息是否过长
     */
    private boolean mesureDescription(TextView shortView, TextView longView) {
        final int shortHeight = shortView.getHeight();
        final int longHeight = longView.getHeight();
        if (longHeight > shortHeight) {
            shortView.setVisibility(View.VISIBLE);
            longView.setVisibility(View.GONE);
            return true;
        }
        shortView.setVisibility(View.GONE);
        longView.setVisibility(View.VISIBLE);
        return false;
    }

    /**
     * 更改按钮【更多】的文本
     */
    private void toogleMoreButton(TextView sTextView) {

        String text = (String) sTextView.getText();

        if (text.equals("展开")) {

            sTextView.setText("收起");
            sTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.mipmap.arrow_up_details, 0);
        } else {

            sTextView.setText("展开");
            sTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.mipmap.arrow_down_details, 0);
        }
    }

    /**
     * App信息是否过长：大于四行则收起，点击展开；反之则不显示按钮
     */
    private void initProductInfo(String details) {

        final FrameLayout frame = (FrameLayout) dView
                .findViewById(R.id.layout_appdetails_fragment_function);
        ViewTreeObserver vto = frame.getViewTreeObserver();

        final TextView appShortDescription = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_function_little);
        appShortDescription.setText(details);
        final TextView appLongDescription = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_function_more);
        appLongDescription.setText(details);

        final TextView more = (TextView) dView
                .findViewById(R.id.textview_appdetails_fragment_function_spread);
        vto.addOnPreDrawListener(new OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (isInit)
                    return true;
                if (mesureDescription(appShortDescription, appLongDescription)) {

                    more.setVisibility(View.VISIBLE);
                }
                isInit = true;
                return true;
            }
        });

        more.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mIsShortDescription) {

                    appShortDescription.setVisibility(View.GONE);
                    appLongDescription.setVisibility(View.VISIBLE);
                } else {

                    appShortDescription.setVisibility(View.VISIBLE);
                    appLongDescription.setVisibility(View.GONE);
                }
                toogleMoreButton(more);
                mIsShortDescription = !mIsShortDescription;
            }
        });
    }

    /**
     * 绑定同类软件
     */
    public void setSameAppData() {

        List<ApkDetalisSameAppBean> sList = apkDetailsBean.getSameAPP();

        NoScrollGridView mGridView = (NoScrollGridView) dView
                .findViewById(R.id.gridview_fragment_appdetails_sameapp);

        SameAppGridViewAdapter sAdapter = new SameAppGridViewAdapter(context,
                sList);
        mGridView.setAdapter(sAdapter);
    }

    /**
     * 创建简介fragment
     *
     * @param aDetailsBean
     * @return
     */
    public static AppDetailsFragment newInstance(ApkDetailsBean aDetailsBean) {

        AppDetailsFragment aFragment = new AppDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DETAILS, (Serializable) aDetailsBean);
        aFragment.setArguments(bundle);
        return aFragment;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.layout_appdetails_fragment_spread:

                if (isexpand) {

                    eLayout.collapse();
                    upTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                            R.mipmap.arrow_down_details, 0);
                    upTextView.setText("展开");
                    isexpand = false;
                } else {

                    eLayout.expand();
                    upTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                            R.mipmap.arrow_up_details, 0);
                    upTextView.setText("收起");
                    isexpand = true;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public String getTitle() {
        return "简介";
    }
}
