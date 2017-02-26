package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.boylw789.edubao.R;

import java.util.ArrayList;
import java.util.List;

import activity.ClassfiydDetailsActivity;
import bean.ChildBean;
import bean.ClassfiyBean;
import view.NoScrollGridView;

public class ClassfiyAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<ClassfiyBean> cList = new ArrayList<ClassfiyBean>();
	private LayoutInflater mLayoutInflater;

	public ClassfiyAdapter(Context context) {

		this.context = context;
		this.mLayoutInflater = LayoutInflater.from(context);
	}

	public void setClassfiyData(List<ClassfiyBean> cList) {

		this.cList = cList;
		this.notifyDataSetChanged();
	}

	@Override
	public Object getChild(int arg0, int arg1) {

		return arg1;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		ClassfiyBean gBean = cList.get(groupPosition);
		List<ChildBean> dataList = gBean.getChildData();
		ItemViewHoder iHoder = null;
		if (convertView == null) {

			iHoder = new ItemViewHoder();
			convertView = mLayoutInflater.inflate(
					R.layout.layout_classfiy_item, parent, false);
			iHoder.mGridView = (NoScrollGridView) convertView
					.findViewById(R.id.gridview_classfiy_item);
			convertView.setTag(iHoder);
		} else {

			iHoder = (ItemViewHoder) convertView.getTag();
		}

		GridAdapter gAdapter = new GridAdapter(dataList, gBean.getGridID(),
				gBean.getGridName());
		iHoder.mGridView.setAdapter(gAdapter);
		return convertView;
	}

	@Override
	public int getChildrenCount(int arg0) {

		return 1;
	}

	@Override
	public Object getGroup(int arg0) {

		return arg0;
	}

	@Override
	public int getGroupCount() {

		return cList.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		ClassfiyBean gBeans = cList.get(groupPosition);
		GroupViewHoder gHoder = null;
		if (convertView == null) {

			gHoder = new GroupViewHoder();
			convertView = mLayoutInflater.inflate(
					R.layout.layout_classfiy_group, parent, false);
			gHoder.gNameTextView = (TextView) convertView
					.findViewById(R.id.textview_classfiy_gridname);
			gHoder.gNumTextView = (TextView) convertView
					.findViewById(R.id.textview_classfiy_num);
			convertView.setTag(gHoder);
		} else {

			gHoder = (GroupViewHoder) convertView.getTag();
		}

		gHoder.gNameTextView.setText(gBeans.getGridName());
		gHoder.gNumTextView.setText(gBeans.getGridAppNum() + "本");
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	class GroupViewHoder {

		TextView gNameTextView;
		TextView gNumTextView;
	}

	class ItemViewHoder {

		NoScrollGridView mGridView;
	}

	public class GridAdapter extends BaseAdapter {

		private List<ChildBean> dataList = new ArrayList<ChildBean>();
		private String gradeId;
		private String gridName;

		public GridAdapter(List<ChildBean> dataList, String gradeId,
				String gridName) {

			this.dataList = dataList;
			this.gradeId = gradeId;
			this.gridName = gridName;
		}

		@Override
		public int getCount() {

			return dataList.size();
		}

		@Override
		public Object getItem(int arg0) {

			return arg0;
		}

		@Override
		public long getItemId(int arg0) {

			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			final ChildBean cBean = dataList.get(position);
			ChildViewHoder cHoder = null;
			if (convertView == null) {

				cHoder = new ChildViewHoder();
				convertView = mLayoutInflater.inflate(
						R.layout.layout_gridview_classfiy_item, parent, false);
				cHoder.cNameTextView = (TextView) convertView
						.findViewById(R.id.textview_classfiy_child_name);
				cHoder.cNumTextView = (TextView) convertView
						.findViewById(R.id.textview_classfiy_child_num);
				convertView.setTag(cHoder);
			} else {

				cHoder = (ChildViewHoder) convertView.getTag();
			}

			cHoder.cNameTextView.setText(cBean.getChildName());
			cHoder.cNumTextView.setText(cBean.getChildAppNum() + "本");

			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					Intent dIntent = new Intent(context,
							ClassfiydDetailsActivity.class);
					dIntent.putExtra("gradeId", gradeId);
					dIntent.putExtra("classifyId", cBean.getChildID());
					dIntent.putExtra("titleName", cBean.getChildName());
					dIntent.putExtra("gridName", gridName);
					context.startActivity(dIntent);
				}
			});

			return convertView;
		}

		class ChildViewHoder {

			TextView cNameTextView;
			TextView cNumTextView;
		}
	}
}
