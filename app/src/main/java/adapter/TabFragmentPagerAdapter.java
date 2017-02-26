package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boylw789.edubao.R;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;

import java.util.ArrayList;

public class TabFragmentPagerAdapter extends IndicatorFragmentPagerAdapter {

	private Context context;
	private ArrayList<Fragment> mFragmentList = null;
	private String[] names;

	public TabFragmentPagerAdapter(Context context,
			FragmentManager fragmentManager, ArrayList<Fragment> fragmentList,
			String[] names) {
		super(fragmentManager);

		this.context = context;
		this.mFragmentList = fragmentList;
		this.names = names;
	}

	@Override
	public int getCount() {
		return mFragmentList.size();
	}

	@Override
	public View getViewForTab(int position, View convertView,
			ViewGroup container) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.tab_top, container, false);
		}
		TextView textView = (TextView) convertView;
		textView.setText(names[position % names.length]);
		int padding = dipToPix(10);
		textView.setPadding(padding, 0, padding, 0);
		return convertView;
	}

	@Override
	public Fragment getFragmentForPage(int position) {

		Fragment fragment = null;
		if (position < mFragmentList.size()) {
			fragment = mFragmentList.get(position);
		} else {
			fragment = mFragmentList.get(0);
		}
		return fragment;
	}

	/**
	 * ����dipֵת����pxֵ
	 * 
	 * @param dip
	 * @return
	 */
	private int dipToPix(float dip) {
		int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dip, context.getResources().getDisplayMetrics());
		return size;
	}
}
