package me.sunlight.sdk.common.widget.toast;

import android.content.Context;

import androidx.annotation.StringRes;

/**
 * <pre>
 *     author : eric
 *     time   : 2019/08/07
 *     desc   :
 *     version:
 * </pre>
 */

public class UIToast {

    public static void init(Context context) {
        ToastHelper.getInstance().init(context);
    }

    public static void showMessage(String text) {
        ToastHelper.getInstance().showMessage(text);
    }

    public static void showMessage(@StringRes int textId) {
        ToastHelper.getInstance().showMessage(textId);
    }

}
