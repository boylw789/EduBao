package model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;
import java.util.Map;

import bean.BannerImagesBean;
import okhttp3.Call;


public class BannerDataModel implements ILoadBannerData {

    @Override
    public void loadBannerData(String urlString,
                               final IOnGetBannerDataListener bListener) {

        OkHttpUtils.get().url(urlString).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String dataString, int id) {

                Map<String, Object> userMap = JSON.parseObject(
                        dataString,
                        new TypeReference<Map<String, Object>>() {
                        });
                if (userMap.get("hasValue").toString()
                        .equalsIgnoreCase("true")) {
                    JSONObject object = JSON.parseObject(dataString);
                    Object jsonArray = object.get("data");
                    List<BannerImagesBean> blist = JSON.parseArray(
                            jsonArray + "", BannerImagesBean.class);
                    bListener.getBannerDataSuccess(blist);
                }
            }
        });
    }
}
