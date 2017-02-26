package activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.boylw789.edubao.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import dmax.dialog.SpotsDialog;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class BaseActivity extends AppCompatActivity {

	private AlertDialog loadingDialog = null;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSystemBar(this);
	}

	/**
	 * 设置状态栏颜色
	 *
	 * @param activity
	 */
	public static void initSystemBar(Activity activity) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

			setTranslucentStatus(activity, true);
		}

		SystemBarTintManager tintManager = new SystemBarTintManager(activity);
		tintManager.setStatusBarTintEnabled(true);

		// 使用颜色资源
		tintManager.setStatusBarTintResource(R.color.backgroud_title);
	}

	private static void setTranslucentStatus(Activity activity, boolean on) {

		Window win = activity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

		if (on) {

			winParams.flags |= bits;
		} else {

			winParams.flags &= ~bits;
		}

		win.setAttributes(winParams);
	}

	/**
	 * 显示进度条对话框
	 *
	 * @param context
	 */
	public void showLoadingDialog(Context context) {

		loadingDialog = new SpotsDialog(context);
		if (loadingDialog != null && !loadingDialog.isShowing()) {

			loadingDialog.show();
		}
	}

	/**
	 * 隐藏进度条对话框
	 */
	public void hideLoadingDialog() {

		if (loadingDialog != null && loadingDialog.isShowing()) {

			loadingDialog.dismiss();
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			View v = getCurrentFocus();
			if (isShouldHideKeyboard(v, ev)) {
				hideKeyboard(v.getWindowToken());
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
	 *
	 * @param v
	 * @param event
	 * @return
	 */
	private boolean isShouldHideKeyboard(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] l = { 0, 0 };
			v.getLocationInWindow(l);
			int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
					+ v.getWidth();
			if (event.getX() > left && event.getX() < right
					&& event.getY() > top && event.getY() < bottom) {
				// 点击EditText的事件，忽略它。
				return false;
			} else {
				v.clearFocus();
				return true;
			}
		}
		// 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
		return false;
	}

	/**
	 * 获取InputMethodManager，隐藏软键盘
	 *
	 * @param token
	 */
	private void hideKeyboard(IBinder token) {
		if (token != null) {
			InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(token,
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
}
