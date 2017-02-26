package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.boylw789.edubao.R;

import activity.AboutAppActivity;
import activity.MyDownLoadActivity;
import activity.PersonalMessageActivity;
import activity.SettingActivity;
import view.CircleImageView;

public class MineFragment extends BaseFragment implements OnClickListener {

    private View mView;
    private Context context;
    private boolean isPrepared;
    private boolean mHasLoadedOnce;
    private CircleImageView cImageView;
    private RelativeLayout pLayout;
    private RelativeLayout dLayout;
    private RelativeLayout sLayout;
    private RelativeLayout aLayout;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {

            context = getActivity();
            mView = inflater.inflate(R.layout.fragment_mine, null, true);
            findViews();
            isPrepared = true;
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {

            parent.removeView(mView);
        }
        return mView;
    }

    public void findViews() {

        cImageView = (CircleImageView) mView
                .findViewById(R.id.circleimageview_mine_photo);

        pLayout = (RelativeLayout) mView
                .findViewById(R.id.layout_mine_personal);
        pLayout.setOnClickListener(this);
        dLayout = (RelativeLayout) mView
                .findViewById(R.id.layout_mine_download);
        dLayout.setOnClickListener(this);
        sLayout = (RelativeLayout) mView.findViewById(R.id.layout_mine_setting);
        sLayout.setOnClickListener(this);
        aLayout = (RelativeLayout) mView
                .findViewById(R.id.layout_mine_aboutApp);
        aLayout.setOnClickListener(this);
    }

    @Override
    protected void lazyLoad() {
        // TODO Auto-generated method stub
        if (!isPrepared || !isVisible || mHasLoadedOnce) {

            return;
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.layout_mine_personal:

                Intent pIntent = new Intent(context, PersonalMessageActivity.class);
                startActivity(pIntent);
                break;
            case R.id.layout_mine_download:

                Intent dIntent = new Intent(context, MyDownLoadActivity.class);
                startActivity(dIntent);
                break;
            case R.id.layout_mine_setting:

                Intent sIntent = new Intent(context, SettingActivity.class);
                startActivity(sIntent);
                break;
            case R.id.layout_mine_aboutApp:

                Intent aIntent = new Intent(context, AboutAppActivity.class);
                startActivity(aIntent);
                break;
            default:
                break;
        }

    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return null;
    }
}
