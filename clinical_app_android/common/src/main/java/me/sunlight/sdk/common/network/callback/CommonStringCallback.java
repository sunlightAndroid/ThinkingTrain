package me.sunlight.sdk.common.network.callback;

import android.accounts.NetworkErrorException;
import android.content.Intent;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.ConnectException;

import me.sunlight.sdk.common.Common;
import me.sunlight.sdk.common.R;
import me.sunlight.sdk.common.app.BaseActivity;
import me.sunlight.sdk.common.app.BaseApplication;
import me.sunlight.sdk.common.app.RxActivityUtils;
import me.sunlight.sdk.common.network.api.ErrorEnum;
import me.sunlight.sdk.common.util.Base64utils;
import me.sunlight.sdk.common.widget.dialog.LoadingView;
import me.sunlight.sdk.common.widget.dialog.loading.LoadingDialog;
import me.sunlight.sdk.common.widget.toast.UIToast;

/**
 * Created by eric
 * 邮箱：1944633835@qq.com
 */
public abstract class CommonStringCallback extends AbsCallback<String> {

    private StringConvert convert;
    private boolean isShowToast = true;

    public CommonStringCallback() {
        convert = new StringConvert();
    }

    public CommonStringCallback(boolean isShowToast) {
        convert = new StringConvert();
        this.isShowToast = isShowToast;
    }

    @Override
    public String convertResponse(okhttp3.Response response) throws Throwable {
        String jsonStr = convert.convertResponse(response);
        response.close();
        String result = Base64utils.getFromBase64(Base64utils.getFromBase64(jsonStr));
        JSONObject obj = new JSONObject(result);
        int status = obj.getInt("code");
        if (status != ErrorEnum.SUCCESS.getCode()) {
            throw new IllegalStateException(obj.getString("msg"));
        }
        return jsonStr;
    }

    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        if (response.getException() instanceof NetworkErrorException) {
            UIToast.showMessage(R.string.api_error_network_exception);
        } else if (response.getException() instanceof ConnectException) {
            UIToast.showMessage(R.string.api_error_server_exception);
        } else {
            if (isShowToast) {
                UIToast.showMessage(response.getException().getMessage());
            }
        }
    }

    @Override
    public void onSuccess(Response<String> response) {
        String result = Base64utils.getFromBase64(Base64utils.getFromBase64(response.body()));
        onSuccess(result);
    }

    protected abstract void onSuccess(String result);
}
