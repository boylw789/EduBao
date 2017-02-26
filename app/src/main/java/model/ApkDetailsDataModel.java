package model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import bean.ApkDetailsBean;
import okhttp3.Call;


public class ApkDetailsDataModel implements ILoadAppDetailsData {

    @Override
    public void loadAppDetailsData(String urlString,
                                   final IOnGetAppDetailsListener listener) {

        OkHttpUtils.get().url(urlString).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                listener.getHttpApkDetailsDataFailed("网络连接失败");
            }

            @Override
            public void onResponse(String dataString, int id) {

                Map<String, Object> userMap = JSON.parseObject(
                        dataString,
                        new TypeReference<Map<String, Object>>() {
                        });

                if (userMap.get("hasValue").toString()
                        .equalsIgnoreCase("true")) {

                    String beanString = userMap.get("data").toString();
                    ApkDetailsBean apkDetailsBean = JSON.parseObject(
                            beanString, ApkDetailsBean.class);
                    listener.getHttpApkDetailsDataSuccess(apkDetailsBean);
                }

            }
        });
    }

}
