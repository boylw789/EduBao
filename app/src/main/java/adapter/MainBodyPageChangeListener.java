package adapter;

import android.support.v4.view.ViewPager.OnPageChangeListener;

import view.CustomRadioGroup;


public class MainBodyPageChangeListener implements OnPageChangeListener {
	
	private CustomRadioGroup customRadioGroup;
	public MainBodyPageChangeListener(CustomRadioGroup c) {
		this.customRadioGroup = c;
	}

	public void onPageScrollStateChanged(int arg0) {
	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {
		if (arg1 != 0.0f) {
			int right, left;
			if (arg0 == customRadioGroup.getCheckedIndex()) {

				left = customRadioGroup.getCheckedIndex();
				right = customRadioGroup.getCheckedIndex() + 1;
			} else {

				left = customRadioGroup.getCheckedIndex() - 1;
				right = customRadioGroup.getCheckedIndex();

			}
			customRadioGroup.itemChangeChecked(left, right, arg1);
		} else {
			customRadioGroup.setCheckedIndex(arg0);
		}
	}

	public void onPageSelected(int arg0) {
	}

}
