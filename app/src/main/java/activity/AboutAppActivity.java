package activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.boylw789.edubao.R;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.List;

import expandablelayout.ExpandableLinearLayout;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class AboutAppActivity extends BaseActivity implements OnClickListener {

	private ExpandableLinearLayout mLinearLayout;
	private boolean isexpand;
	private ImageView upImageView;
	private ImageView bImageView;
	private RelativeLayout sLayout;

	private WheelView wheelView;
	private List<String> nameList;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutapp);
		TextView versionTextView = (TextView) findViewById(R.id.textview_aboutapp_version);
		versionTextView.setText("版本:" + getAPPVersion());
		findViews();
	}

	/**
	 * 初始化
	 */
	public void findViews() {

		mLinearLayout = (ExpandableLinearLayout) findViewById(R.id.layout_aboutapp_container);
		mLinearLayout.expand();// 默认展开，否则滚轮style设置无效

		sLayout = (RelativeLayout) findViewById(R.id.layout_aboutapp_spreed);
		sLayout.setOnClickListener(this);
		upImageView = (ImageView) findViewById(R.id.imageview_aboutapp_spreed);
		bImageView = (ImageView) findViewById(R.id.imageview_aboutapp_back);
		bImageView.setOnClickListener(this);

		wheelView = (WheelView) findViewById(R.id.wheelview_aboutapp_team);
		wheelView.setWheelAdapter(new ArrayWheelAdapter(this));
		wheelView.setWheelSize(5);
		wheelView.setLoop(true);
		wheelView.setSkin(WheelView.Skin.Holo);
		wheelView.setSelection(0);
		WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
		style.selectedTextColor = ContextCompat.getColor(this,
				R.color.backgroud_title);
		style.selectedTextSize = 18;
		style.textColor = Color.GRAY;
		wheelView.setStyle(style);
		nameList = new ArrayList<String>();
		nameList.add("fastjson by alibaba");
		nameList.add("glide by bumptech");
		nameList.add("nineoldandroids");
		nameList.add("okhttp/square");
		nameList.add("okhttputils by hongyangAndroid");
		nameList.add("systembartint/jgilfelt");
		nameList.add("switchbutton/zcweng");
		nameList.add("viewpagerindicator/LuckyJayce");
		nameList.add("wheeview by venshine");
		nameList.add("Aspsine/SwipeToLoadLayout");
		nameList.add("NumberProgressBar by daimajia");
		nameList.add("StickyNavLayout by guxiuzhong");
		nameList.add("ActionSheetDialog/liuqiang");
		nameList.add("SpotsDialog/Maxim Dybarsky");
		nameList.add("RatingBar by hedge_hog");
		wheelView.setWheelData(nameList);
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

			case R.id.imageview_aboutapp_back:

				finish();
				break;
			case R.id.layout_aboutapp_spreed:

				if (isexpand) {

					mLinearLayout.expand();
					upImageView.setImageResource(R.mipmap.arrow_up_aboutapp);
					isexpand = false;
				} else {

					mLinearLayout.collapse();
					upImageView.setImageResource(R.mipmap.arrow_down_aboutapp);
					isexpand = true;
				}
				break;
			default:
				break;
		}
	}

	/**
	 * 获取软件版本号
	 */
	private String getAPPVersion() {

		PackageManager pm = this.getPackageManager();// 得到PackageManager对象
		PackageInfo pi = null;
		try {

			pi = pm.getPackageInfo(this.getPackageName(), 0);// 得到PackageInfo对象，封装了一些软件包的信息在里面
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return pi.versionName;
	}
}
