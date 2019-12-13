package me.sunlight.sdk.common.widget.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.wang.avi.AVLoadingIndicatorView;

import me.sunlight.sdk.common.R;

/**
 * <pre>
 *     author : 戈传光
 *     e-mail : 1944633835@qq.com
 *     time   : 2018/07/11
 *     desc   :
 *     version:
 * </pre>
 */
public class LoadingView extends RelativeLayout {
    AVLoadingIndicatorView avi;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.layout_loading_view, this, true);

        avi = inflate.findViewById(R.id.avi_loading);
    }

    public void showLoading() {
        if (avi != null)
            avi.show();
        LoadingView.this.setVisibility(View.VISIBLE);
    }

    public void hide() {
        if (avi != null)
            avi.hide();
        LoadingView.this.setVisibility(View.GONE);
        this.setPadding(0, 0, 0, 0);
    }
}
