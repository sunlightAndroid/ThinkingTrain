package me.sunlight.sdk.common.network.callback;

import android.accounts.NetworkErrorException;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;

import me.sunlight.sdk.common.Common;
import me.sunlight.sdk.common.R;
import me.sunlight.sdk.common.app.BaseApplication;
import me.sunlight.sdk.common.network.api.ErrorEnum;
import me.sunlight.sdk.common.network.api.ResponseModel;
import me.sunlight.sdk.common.widget.toast.UIToast;
import okhttp3.ResponseBody;

/**
 * <pre>
 *     author : eric
 *     e-mail : 1944633835@qq.com
 * </pre>
 */
public abstract class JsonCallback<T> extends AbsCallback<T> {

    private Class<T> mClass;
    private Type mType;

    public JsonCallback() {
    }


    public JsonCallback(Class<T> aClass) {
        mClass = aClass;
    }

    public JsonCallback(Type type) {
        mType = type;
    }


    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {

        ResponseBody body = response.body();
        if (body == null) return null;

        T data;

        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());

        if (mClass != null) {
            data = gson.fromJson(jsonReader, mClass);
        } else if (mType != null) {
            data = gson.fromJson(jsonReader, mType);
        } else {

            Type genType = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            ResponseModel model = gson.fromJson(jsonReader, type);
            // 重新登陆的意思
            if (model.status == ErrorEnum.NEED_LOGIN.getCode())
                BaseApplication.getInstance().sendBroadcast(new Intent(Common.Constants.OFFLINE_ACTION));

            if (model.status != ErrorEnum.SUCCESS.getCode()) {
                // 这里要根据服务器返回的错误码决定
                throw new IllegalAccessException(model.message);
            }
            data = (T) model;
        }
        return data;
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);

        if (response.getException() instanceof NetworkErrorException) {
            UIToast.showMessage(R.string.api_error_network_exception);
        } else if (response.getException() instanceof ConnectException) {
            UIToast.showMessage(R.string.api_error_server_exception);
        }
    }
}
