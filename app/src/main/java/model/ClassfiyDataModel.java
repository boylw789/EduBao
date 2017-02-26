package model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;
import java.util.Map;

import bean.ClassfiyBean;
import okhttp3.Call;


public class ClassfiyDataModel implements ILoadClassfiyData {

    @Override
    public void loadClassData(String urlString,
                              final IOnGetClassfiyDataListener listener) {

        OkHttpUtils.get().url(urlString).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                listener.getClassfiyDataFailed("网络连接失败！");
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
                    List<ClassfiyBean> list = JSON.parseArray(jsonArray
                            + "", ClassfiyBean.class);
                    listener.getClassfiyDataSuccess(list);
                }
            }
        });
    }
}
