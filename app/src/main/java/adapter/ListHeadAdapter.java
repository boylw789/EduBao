package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.boylw789.edubao.R;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import activity.AppDetailsActivity;
import bean.ApkInfoBean;
import bean.BannerImagesBean;
import view.NumberProgressBar;

public class ListHeadAdapter extends
        RecyclerView.Adapter<ViewHolder> {

    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    private LayoutInflater mLayoutInflater;
    private Context context;
    private int mHeaderCount = 1;//
    private List<ApkInfoBean> hlist = new ArrayList<ApkInfoBean>();
    private List<BannerImagesBean> bList = new ArrayList<BannerImagesBean>();

    public ListHeadAdapter(Context context) {

        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * @param hlist
     */
    public void setList(List<ApkInfoBean> hlist) {

        this.hlist = hlist;
        this.notifyDataSetChanged();
    }

    /**
     * @param bList
     */
    public void setBanner(List<BannerImagesBean> bList) {

        this.bList = bList;
    }

    public int getContentItemCount() {

        return hlist.size();
    }

    public boolean isHeaderView(int position) {

        return mHeaderCount == 1 && position == 0;
    }

    @Override
    public int getItemViewType(int position) {

        if (isHeaderView(position)) {

            return ITEM_TYPE_HEADER;
        } else {

            return ITEM_TYPE_CONTENT;
        }
    }

    class ContentViewHolder extends ViewHolder {

        ImageView mImageView;
        TextView nTextView;
        TextView dTextView;
        TextView gTextView;
        TextView cTextView;
        TextView speedTextView;
        TextView counTextView;
        TextView sizeTextView;
        Button button;
        private NumberProgressBar progressBar;

        public ContentViewHolder(View view) {
            super(view);

            mImageView = (ImageView) view.findViewById(R.id.item_imageview);
            nTextView = (TextView) view.findViewById(R.id.item_name_textview);
            gTextView = (TextView) view
                    .findViewById(R.id.item_gridname_textview);
            cTextView = (TextView) view
                    .findViewById(R.id.item_childname_textview);
            dTextView = (TextView) view
                    .findViewById(R.id.item_detalis_textview);
            counTextView = (TextView) view
                    .findViewById(R.id.item_count_textview);
            speedTextView = (TextView) view
                    .findViewById(R.id.item_netSpeed_textview);
            sizeTextView = (TextView) view
                    .findViewById(R.id.item_downloadSize_textview);
            button = (Button) view.findViewById(R.id.item_download_button);
            progressBar = (NumberProgressBar) view
                    .findViewById(R.id.item_download_progressbar);
        }
    }

    class HeaderViewHolder extends ViewHolder {

        ViewPager viewPager;
        Indicator indicator;

        public HeaderViewHolder(View view) {
            super(view);

            viewPager = (ViewPager) view.findViewById(R.id.banner_viewPager);
            indicator = (Indicator) view.findViewById(R.id.banner_indicator);
        }
    }

    @Override
    public int getItemCount() {

        return mHeaderCount + getContentItemCount();
    }

    @Override
    public void onBindViewHolder(ViewHolder mHoder, int position) {

        if (mHoder instanceof HeaderViewHolder) {

            if (bList != null && bList.size() > 0) {

                BannerComponent bannerComponent = new BannerComponent(
                        ((HeaderViewHolder) mHoder).indicator,
                        ((HeaderViewHolder) mHoder).viewPager, false);
                MyBannerAdapter mAdapter = new MyBannerAdapter(bList);
                bannerComponent.setAdapter(mAdapter);
                bannerComponent.startAutoPlay();
                bannerComponent.setAutoPlayTime(5000);
            } else {

                return;
            }

        } else if (mHoder instanceof ContentViewHolder) {

            final ApkInfoBean lBean = hlist.get(position - mHeaderCount);

            ((ContentViewHolder) mHoder).nTextView.setText(lBean.getName());
            ((ContentViewHolder) mHoder).cTextView
                    .setText(lBean.getChildName());
            ((ContentViewHolder) mHoder).gTextView.setText(lBean.getGridName());
            ((ContentViewHolder) mHoder).dTextView.setText(lBean.getDetails());
            ((ContentViewHolder) mHoder).counTextView.setText(lBean
                    .getDownloadCount() + "次");
            Glide.with(context).load(lBean.getIcon())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(((ContentViewHolder) mHoder).mImageView);

            mHoder.itemView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    Intent dIntent = new Intent(context,
                            AppDetailsActivity.class);
                    dIntent.putExtra("id", lBean.getId());
                    context.startActivity(dIntent);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_HEADER) {

            HeaderViewHolder hViewHolder = new HeaderViewHolder(
                    mLayoutInflater.inflate(R.layout.layout_head_banner,
                            parent, false));
            hViewHolder.setIsRecyclable(false);
            return hViewHolder;
        } else if (viewType == ITEM_TYPE_CONTENT) {

            View view = mLayoutInflater.inflate(R.layout.list_item, parent,
                    false);
            ContentViewHolder cViewHolder = new ContentViewHolder(view);
            return cViewHolder;
        }
        return null;
    }

    public class MyBannerAdapter extends IndicatorViewPagerAdapter {

        List<BannerImagesBean> tempbList = new ArrayList<BannerImagesBean>();

        public MyBannerAdapter(List<BannerImagesBean> bList) {

            this.tempbList = bList;
        }

        @Override
        public int getCount() {

            return tempbList.size();
        }

        @Override
        public View getViewForPage(int position, View convertView,
                                   ViewGroup container) {

            if (convertView == null) {
                convertView = new ImageView(context);
                convertView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
            }

            ImageView imageView = (ImageView) convertView;
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            BannerImagesBean imagesBean = tempbList.get(position);
            Glide.with(context).load(imagesBean.getImagePath())
                    .error(R.mipmap.banner_nopic).into(imageView);
            return convertView;
        }

        @Override
        public View getViewForTab(int position, View convertView,
                                  ViewGroup container) {

            if (convertView == null) {

                convertView = mLayoutInflater.inflate(R.layout.view_tab_banner,
                        container, false);
            }
            return convertView;
        }
    }
}
