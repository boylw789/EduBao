package activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.boylw789.edubao.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle arg0) {
        super.onCreate(arg0);
        initSystemBar(this);
    }

    public static void initSystemBar(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            setTranslucentStatus(activity, true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);

        tintManager.setStatusBarTintResource(R.color.backgroud_title);
    }

    private static void setTranslucentStatus(Activity activity, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

        if (on) {

            winParams.flags |= bits;
        } else {

            winParams.flags &= ~bits;
        }

        win.setAttributes(winParams);
    }
}
