package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.boylw789.edubao.R;
import com.suke.widget.SwitchButton;

import bean.SettingUtils;

public class SettingActivity extends BaseActivity {

    private SwitchButton deleteToggleButton;
    private SwitchButton cleanToggleButton;
    private SwitchButton installToggleButton;
    private SwitchButton updateToggleButton;
    private SwitchButton fastToggleButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViews();
        setViews();
    }

    public void findViews() {

        ImageView bImageView = (ImageView) findViewById(R.id.imageview_setting_back);
        bImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                finish();
            }
        });

        deleteToggleButton = (SwitchButton) findViewById(R.id.togglebutton_setting_deleteapk);
        cleanToggleButton = (SwitchButton) findViewById(R.id.togglebutton_setting_cleanram);
        installToggleButton = (SwitchButton) findViewById(R.id.togglebutton_setting_installself);
        updateToggleButton = (SwitchButton) findViewById(R.id.togglebutton_setting_updateapk);
        fastToggleButton = (SwitchButton) findViewById(R.id.togglebutton_setting_fastdownload);

        toggleListener();
    }


    public void toggleListener() {

        deleteToggleButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean on) {

                if (on) {

                    SettingUtils.set(SettingActivity.this,
                            SettingUtils.DELETE_APK, on);
                } else {

                    SettingUtils.set(SettingActivity.this,
                            SettingUtils.DELETE_APK, on);

                }
            }
        });
        cleanToggleButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean on) {

                if (on) {

                    SettingUtils.set(SettingActivity.this, SettingUtils.CLEAN_RAM,
                            on);
                }
            }
        });

        installToggleButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                SettingUtils.set(SettingActivity.this,
                        SettingUtils.INSTALL_SELF, isChecked);
            }
        });

        updateToggleButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                SettingUtils.set(SettingActivity.this,
                        SettingUtils.UPDATE_WIFI, isChecked);
            }
        });

        fastToggleButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                SettingUtils.set(SettingActivity.this,
                        SettingUtils.FAST_DOWNLOAD, isChecked);
            }
        });
    }

    public void setViews() {

        boolean isdelete = SettingUtils.get(SettingActivity.this,
                SettingUtils.DELETE_APK, false);
        if (isdelete) {

            deleteToggleButton.isChecked();
            deleteToggleButton.setChecked(false);
        } else {

            deleteToggleButton.toggle();
            deleteToggleButton.toggle(false);
        }

        boolean isclean = SettingUtils.get(SettingActivity.this,
                SettingUtils.CLEAN_RAM, false);
        if (isclean) {

            cleanToggleButton.isChecked();
            cleanToggleButton.setChecked(false);
        } else {

            cleanToggleButton.toggle();
            cleanToggleButton.toggle(false);
        }


        boolean isinstall = SettingUtils.get(SettingActivity.this,
                SettingUtils.INSTALL_SELF, false);
        if (isinstall) {

            installToggleButton.isChecked();
            installToggleButton.setChecked(false);
        } else {

            installToggleButton.toggle();
            installToggleButton.toggle(false);
        }

        boolean isupdate = SettingUtils.get(SettingActivity.this,
                SettingUtils.UPDATE_WIFI, false);
        if (isupdate) {

            updateToggleButton.isChecked();
            updateToggleButton.setChecked(false);
        } else {

            updateToggleButton.toggle();
            updateToggleButton.toggle(false);
        }

        boolean isfast = SettingUtils.get(SettingActivity.this,
                SettingUtils.FAST_DOWNLOAD, false);
        if (isfast) {

            fastToggleButton.isChecked();
            fastToggleButton.setChecked(false);
        } else {

            fastToggleButton.toggle();
            fastToggleButton.toggle(false);
        }
    }
}
