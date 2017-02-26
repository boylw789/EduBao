package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.boylw789.edubao.R;

import java.util.ArrayList;
import java.util.List;

import bean.ApkInfoBean;
import bean.HelperUtils;
import view.NumberProgressBar;
import view.RatingBar;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHoder> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<ApkInfoBean> list = new ArrayList<ApkInfoBean>();
    private static final String SEARCH_ADPTER_TYPE = "SEARCH_ADPTER_TYPE";
    private String ADPTER_TYPE;
    private static final String RANK_ADPTER_TYPE = "RANK_ADPTER_TYPE";
    private static final String NORMAL_ADPTER_TYPE = "NORMAL_ADPTER_TYPE";

    private String searchString;

    public ListAdapter(Context context, String ADPTER_TYPE) {

        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.ADPTER_TYPE = ADPTER_TYPE;
    }

    /**
     * @param list
     */
    public void SetData(List<ApkInfoBean> list, String searchString) {

        this.list = list;
        this.searchString = searchString;
        this.notifyDataSetChanged();
    }

    class MyViewHoder extends ViewHolder {

        ImageView mImageView;

        TextView nTextView;

        TextView dTextView;

        TextView speedTextView;

        TextView counTextView;

        TextView sizeTextView;

        Button button;

        private NumberProgressBar progressBar;

        private RatingBar mRatingBar;

        private ImageView fImageView, sImageView, tImageView;

        private int position;

        public MyViewHoder(View view) {
            super(view);

            mImageView = (ImageView) view
                    .findViewById(R.id.item_imageview_rank);
            nTextView = (TextView) view
                    .findViewById(R.id.item_name_textview_rank);
            dTextView = (TextView) view
                    .findViewById(R.id.item_detalis_textview_rank);
            counTextView = (TextView) view
                    .findViewById(R.id.item_count_textview_rank);
            speedTextView = (TextView) view
                    .findViewById(R.id.item_netSpeed_textview_rank);
            sizeTextView = (TextView) view
                    .findViewById(R.id.item_downloadSize_textview_rank);
            button = (Button) view.findViewById(R.id.item_download_button_rank);
            progressBar = (NumberProgressBar) view
                    .findViewById(R.id.item_download_progressbar_rank);
            mRatingBar = (RatingBar) view.findViewById(R.id.ratingbar_rank);
            fImageView = (ImageView) view
                    .findViewById(R.id.imageview_rank_first);
            sImageView = (ImageView) view
                    .findViewById(R.id.imageview_rank_second);
            tImageView = (ImageView) view
                    .findViewById(R.id.imageview_rank_third);
        }

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHoder mHoder, int position) {


        ApkInfoBean lBean = list.get(position);
        mHoder.mRatingBar.setStar(Float.parseFloat(lBean.getAvgScore()));
        mHoder.counTextView.setText(lBean.getDownloadCount() + "次");
        Glide.with(context).load(lBean.getIcon())
                .placeholder(R.mipmap.ic_launcher).into(mHoder.mImageView);

        switch (ADPTER_TYPE) {

            case SEARCH_ADPTER_TYPE:

                SpannableStringBuilder name = HelperUtils.highlight(context,
                        lBean.getName(), searchString);
                SpannableStringBuilder details = HelperUtils.highlight(context,
                        lBean.getDetails(), searchString);

                mHoder.nTextView.setText(name);
                mHoder.dTextView.setText(details);

                mHoder.sImageView.setVisibility(View.GONE);
                mHoder.fImageView.setVisibility(View.GONE);
                mHoder.tImageView.setVisibility(View.GONE);
                break;
            case RANK_ADPTER_TYPE:

                mHoder.nTextView.setText(lBean.getName());
                mHoder.dTextView.setText(lBean.getDetails());
                if (position == 0) {

                    mHoder.fImageView.setVisibility(View.VISIBLE);
                    mHoder.sImageView.setVisibility(View.GONE);
                    mHoder.tImageView.setVisibility(View.GONE);

                } else if (position == 1) {

                    mHoder.sImageView.setVisibility(View.VISIBLE);
                    mHoder.fImageView.setVisibility(View.GONE);
                    mHoder.tImageView.setVisibility(View.GONE);

                } else if (position == 2) {

                    mHoder.tImageView.setVisibility(View.VISIBLE);
                    mHoder.sImageView.setVisibility(View.GONE);
                    mHoder.fImageView.setVisibility(View.GONE);
                } else {

                    mHoder.sImageView.setVisibility(View.GONE);
                    mHoder.fImageView.setVisibility(View.GONE);
                    mHoder.tImageView.setVisibility(View.GONE);
                }
                break;
            case NORMAL_ADPTER_TYPE:

                mHoder.nTextView.setText(lBean.getName());
                mHoder.dTextView.setText(lBean.getDetails());

                mHoder.sImageView.setVisibility(View.GONE);
                mHoder.fImageView.setVisibility(View.GONE);
                mHoder.tImageView.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHoder mHoder = new MyViewHoder(mLayoutInflater.inflate(
                R.layout.list_item_rank, parent, false));
        return mHoder;
    }
}
