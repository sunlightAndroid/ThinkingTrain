package me.sunlight.sdk.common.widget.toast;

import android.content.Context;

import androidx.annotation.StringRes;

/**
 * <pre>
 *     author : eric
 *     time   : 2019/07/18
 *     desc   :
 *     version:
 * </pre>
 */

public interface ToastInterface {

    void  init(Context context);
    void showMessage(String text);
    void showMessage(@StringRes int id);
}
