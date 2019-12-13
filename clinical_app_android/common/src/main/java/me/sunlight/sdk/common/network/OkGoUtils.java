package me.sunlight.sdk.common.network;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import me.sunlight.sdk.common.network.callback.CommonStringCallback;
import me.sunlight.sdk.common.network.callback.CommonStringLoadingCallback;
import me.sunlight.sdk.common.util.Base64utils;
import me.sunlight.sdk.common.util.Utils;

/**
 * <pre>
 *     author : eric
 *     time   : 2019/12/11
 *     desc   : 网络请求层wrapper
 *     version: 1.0
 * </pre>
 */
public class OkGoUtils {

    public static void post(String url, String jsonParams, CommonStringCallback callback) {
        String base64 = Base64utils.getBase64(Base64utils.getBase64(jsonParams));

        JSONObject dataCheckJson = new JSONObject();
        try {
            dataCheckJson.put("userAccount", Utils.getAccount());
            dataCheckJson.put("password", Utils.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String datacheck = Base64utils.getBase64(Base64utils.getBase64(dataCheckJson.toString()));

        OkGo.<String>post(url)
                .params("data", base64)
                .params("datacheck", datacheck)
                .execute(callback);
    }


    public static void post(String url, String jsonParams, CommonStringLoadingCallback callback) {
        String base64 = Base64utils.getBase64(Base64utils.getBase64(jsonParams));

        JSONObject dataCheckJson = new JSONObject();
        try {
            dataCheckJson.put("userAccount", Utils.getAccount());
            dataCheckJson.put("password", Utils.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String datacheck = Base64utils.getBase64(Base64utils.getBase64(dataCheckJson.toString()));

        OkGo.<String>post(url)
                .params("data", base64)
                .params("datacheck", datacheck)
                .execute(callback);
    }

}
