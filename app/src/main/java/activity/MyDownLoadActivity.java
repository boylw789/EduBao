package activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.boylw789.edubao.R;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;
import java.util.List;

import adapter.TabFragmentPagerAdapter;
import fragment.MydownloadingFragment;
import fragment.MyfinishdownloadFragment;

public class MyDownLoadActivity extends BaseFragmentActivity {

    private ImageView bImageView;

    private ViewPager viewPager;
    private ArrayList<Fragment> pagerItemList = null;
    private ScrollIndicatorView indicatorView;
    private IndicatorViewPager indicatorViewPager;
    private int unSelectTextColor;

    private String[] names = {"正在下载", "下载完成"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydownload);
        findViews();
        init();
    }

    /**
     * �ؼ�
     */
    public void findViews() {

        indicatorView = (ScrollIndicatorView) findViewById(R.id.indicator_mydownload);
        viewPager = (ViewPager) findViewById(R.id.viewpager_mydownload);

        bImageView = (ImageView) findViewById(R.id.imageview_mydownload_back);
        bImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                finish();
            }
        });
    }

    public void init() {

        int seletedColor = ContextCompat
                .getColor(this, R.color.backgroud_title);
        indicatorView.setScrollBar(new ColorBar(this, seletedColor, 2));
        unSelectTextColor = Color.GRAY;

        indicatorView.setOnTransitionListener(new OnTransitionTextListener()
                .setColor(seletedColor, unSelectTextColor));

        indicatorViewPager = new IndicatorViewPager(indicatorView, viewPager);

        MydownloadingFragment page1 = new MydownloadingFragment();
        MyfinishdownloadFragment page2 = new MyfinishdownloadFragment();

        List<Fragment> listFragments = new ArrayList<Fragment>();
        listFragments.add(page1);
        listFragments.add(page2);

        pagerItemList = new ArrayList<Fragment>();
        pagerItemList.addAll(listFragments);
        FragmentManager fManager = this.getSupportFragmentManager();

        viewPager.setOffscreenPageLimit(0);
        indicatorViewPager.setAdapter(new TabFragmentPagerAdapter(this, fManager, pagerItemList,
                names));
    }
}
