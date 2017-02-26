package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.boylw789.edubao.R;

import java.util.ArrayList;
import java.util.List;


public class AppdetailsImgAdapter extends
		RecyclerView.Adapter<AppdetailsImgAdapter.ImgViewHoder> {

	private Context context;
	private LayoutInflater mInflater;
	private List<String> imgs = new ArrayList<String>();

	public AppdetailsImgAdapter(Context context, List<String> imgs) {

		this.context = context;
		this.imgs = imgs;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getItemCount() {

		return imgs.size();
	}

	@Override
	public void onBindViewHolder(ImgViewHoder iViewHoder, int position) {

		String urlString = imgs.get(position);
		if (!TextUtils.isEmpty(urlString) && urlString != null) {

			Glide.with(context).load(imgs.get(position))
					.placeholder(R.mipmap.appdetails_pic_error)
					.error(R.mipmap.appdetails_pic_error)
					.into(iViewHoder.imageView);
		}
	}

	@Override
	public ImgViewHoder onCreateViewHolder(ViewGroup parent, int arg1) {

		ImgViewHoder iHoder = new ImgViewHoder(mInflater.inflate(
				R.layout.item_appdetails_fragment_img, parent, false));

		return iHoder;
	}

	class ImgViewHoder extends ViewHolder {

		private ImageView imageView;

		public ImgViewHoder(View view) {
			super(view);

			imageView = (ImageView) view
					.findViewById(R.id.imageview_appdetails_fragment_item);
		}

	}
}
