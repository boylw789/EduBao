package model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;
import java.util.Map;

import bean.ApkInfoBean;
import okhttp3.Call;


public class ListDataModel implements ILoadHttpData {

    @Override
    public void loadData(String urlString, final IOnGethttpDataListener listener) {


        OkHttpUtils.get().url(urlString).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                listener.getHttpDataFailed("网络连接失败！");
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
                    List<ApkInfoBean> list = JSON.parseArray(jsonArray
                            + "", ApkInfoBean.class);
                    int recordCount = Integer.parseInt(userMap.get(
                            "recordCount").toString());
                    listener.getHttpDataSuccess(list, recordCount);
                } else {

                    listener.getHttpDataNull();
                }
            }
        });
    }

}
