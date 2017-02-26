package fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.Fragment;

import dmax.dialog.SpotsDialog;


public abstract class BaseFragment extends Fragment {

    protected boolean isVisible;

    private AlertDialog loadingDialog = null;

    public abstract String getTitle();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    public void showLoadingDialog(Context context) {

        loadingDialog = new SpotsDialog(context);
        if (loadingDialog != null && !loadingDialog.isShowing()) {

            loadingDialog.show();
        }
    }

    public void hideLoadingDialog() {

        if (loadingDialog != null && loadingDialog.isShowing()) {

            loadingDialog.dismiss();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {

    }

    protected abstract void lazyLoad();
}
