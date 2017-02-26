package com.example.boylw789.edubao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import activity.BaseFragmentActivity;
import adapter.AbFragmentPagerAdapter;
import adapter.MainBodyPageChangeListener;
import fragment.ClassifyFragment;
import fragment.MineFragment;
import fragment.RecommendFragment;
import fragment.RefinedProductFragment;
import view.CustomRadioGroup;

public class MainActivity extends BaseFragmentActivity {

    private CustomRadioGroup footer;
    private ViewPager viewpager;
    private int[] itemImage = { R.mipmap.resume_off, R.mipmap.findjob_off,
            R.mipmap.message_off, R.mipmap.personal_off };
    private int[] itemCheckedImage = { R.mipmap.resume_on,
            R.mipmap.findjob_on, R.mipmap.message_on,
            R.mipmap.personal_on };
    private String[] itemText = { "精品", "推荐", "分类", "个人中心", };
    /** 内容区域的适配器. */
    private AbFragmentPagerAdapter mFragmentPagerAdapter = null;
    /** 内容的View. */
    private ArrayList<Fragment> pagerItemList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化控件
     */
    private void init() {

        footer = (CustomRadioGroup) findViewById(R.id.main_footer);
        for (int i = 0; i < itemImage.length; i++) {

            footer.addItem(itemImage[i], itemCheckedImage[i], itemText[i]);
        }

        viewpager = (ViewPager) findViewById(R.id.main_viewpager);

        RefinedProductFragment page1 = new RefinedProductFragment();
        RecommendFragment page2 = new RecommendFragment();
        ClassifyFragment page3 = new ClassifyFragment();
        MineFragment page4 = new MineFragment();

        List<Fragment> mFragments = new ArrayList<Fragment>();
        mFragments.add(page1);
        mFragments.add(page2);
        mFragments.add(page3);
        mFragments.add(page4);
        pagerItemList = new ArrayList<Fragment>();
        pagerItemList.addAll(mFragments);

        FragmentManager fManager = this.getSupportFragmentManager();
        mFragmentPagerAdapter = new AbFragmentPagerAdapter(fManager,
                pagerItemList);
        viewpager.setAdapter(mFragmentPagerAdapter);

        // 滑动
        final MainBodyPageChangeListener bodyChangeListener = new MainBodyPageChangeListener(
                footer);
        viewpager.addOnPageChangeListener(bodyChangeListener);

        // 取消预加载，默认只加载1个Fragment
        viewpager.setOffscreenPageLimit(0);
        footer.setCheckedIndex(viewpager.getCurrentItem());
        footer.setOnItemChangedListener(new CustomRadioGroup.OnItemChangedListener() {
            public void onItemChanged() {
                viewpager.setCurrentItem(footer.getCheckedIndex(), false);
            }
        });
    }
}

