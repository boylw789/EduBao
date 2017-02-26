package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.example.boylw789.edubao.R;

public class MydownloadingFragment extends BaseFragment implements
        OnItemLongClickListener {

    private View dView;
    private Context context;
    private boolean isPrepared;
    private boolean mHasLoadedOnce;
    private ListView mListView;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (dView == null) {

            context = getActivity();
            dView = inflater.inflate(R.layout.fragment_mydownoad, null, true);
            isPrepared = true;
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) dView.getParent();
        if (parent != null) {

            parent.removeView(dView);
        }
        return dView;
    }

    public void init() {

        mListView = (ListView) dView.findViewById(R.id.listview_mydownload);
        mListView.setOnItemLongClickListener(this);

    }

    @Override
    protected void lazyLoad() {

        if (!isPrepared || !isVisible || mHasLoadedOnce) {

            return;
        }
        init();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                   int position, long arg3) {

        return false;
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return null;
    }
}
