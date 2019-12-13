package me.sunlight.sdk.common.widget.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.sunlight.sdk.common.R;


/**
 * <pre>
 *     author : eric
 *     time   : 2019/07/18
 *     desc   :
 *     version:
 * </pre>
 */
public class ToastHelper implements ToastInterface{

    private Toast mToast;

    private ToastHelper() {
    }

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static ToastHelper singletonState = new ToastHelper();
    }

    public static ToastHelper getInstance() {
        return SingletonHolder.singletonState;
    }

    private Context mContext;
    private TextView toastTextView;
    private ImageView toastIcon;
    private View toastLayout;

    private Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    public void init(Context context) {
        mContext = context;
        mToast = new Toast(mContext);
        LayoutInflater inflater = LayoutInflater.from(context);
        toastLayout = inflater.inflate(R.layout.toast_default_layout, null);
        toastTextView = (TextView) toastLayout.findViewById(R.id.toast_text);
        mToast.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL, 0, 0);
    }

    @Override
    public void showMessage(String text) {
        mHandler.post(() -> {
            toastTextView.setText(text);
            mToast.setView(toastLayout);
            mToast.cancel();
            mHandler.postDelayed(() -> mToast.show(),200);
        });
    }

    @Override
    public void showMessage(int textId) {
        mHandler.post(() -> {
            toastTextView.setText(textId);
            mToast.setView(toastLayout);
            mToast.cancel();
            mHandler.postDelayed(() -> mToast.show(),200);
        });
    }
}
