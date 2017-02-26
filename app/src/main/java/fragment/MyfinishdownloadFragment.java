package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.boylw789.edubao.R;

public class MyfinishdownloadFragment extends BaseFragment {

    private View fView;
    private Context context;

    private boolean isPrepared;
    private boolean mHasLoadedOnce;
    private ListView fListView;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (fView == null) {

            context = getActivity();
            fView = inflater.inflate(R.layout.fragment_myfinishdownload, null,
                    true);
            isPrepared = true;
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) fView.getParent();
        if (parent != null) {

            parent.removeView(fView);
        }
        return fView;
    }

    public void init() {

        fListView = (ListView) fView.findViewById(R.id.listview_myfinish);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void lazyLoad() {

        if (!isPrepared || !isVisible || mHasLoadedOnce) {

            return;
        }
        init();
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return null;
    }
}
