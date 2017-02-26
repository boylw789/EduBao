package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.boylw789.edubao.R;

import java.util.ArrayList;
import java.util.List;

import activity.AppDetailsActivity;
import bean.ApkDetalisSameAppBean;


public class SameAppGridViewAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mLayoutInflater;
	private List<ApkDetalisSameAppBean> sList = new ArrayList<ApkDetalisSameAppBean>();

	public SameAppGridViewAdapter(Context context,
			List<ApkDetalisSameAppBean> sList) {

		this.context = context;
		this.mLayoutInflater = LayoutInflater.from(context);
		this.sList = sList;
	}

	@Override
	public int getCount() {

		return sList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final ApkDetalisSameAppBean sBean = sList.get(position);

		if (convertView == null) {

			convertView = mLayoutInflater.inflate(
					R.layout.item_appdetails_fragment_sameapp, parent, false);
			ImageView mImageView = (ImageView) convertView
					.findViewById(R.id.imageview_appdetails_fragment_sameapp_item);
			TextView mTextView = (TextView) convertView
					.findViewById(R.id.textview_appdetails_fragment_sameapp_name);

			Glide.with(context).load(sBean.getIcon())
					.placeholder(R.mipmap.ic_launcher)
					.error(R.mipmap.ic_launcher).into(mImageView);
			mTextView.setText(sBean.getName());
		}

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent aIntent = new Intent(context, AppDetailsActivity.class);
				aIntent.putExtra("id", sBean.getId());
				context.startActivity(aIntent);
			}
		});
		return convertView;
	}

}
