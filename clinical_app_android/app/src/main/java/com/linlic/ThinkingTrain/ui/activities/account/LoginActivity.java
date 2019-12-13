package com.linlic.ThinkingTrain.ui.activities.account;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.linlic.ThinkingTrain.MainActivity;
import com.linlic.ThinkingTrain.R;

import butterknife.BindView;
import me.sunlight.sdk.common.app.BaseActivity;
import me.sunlight.sdk.common.widget.titlebar.CommonTitleBar;


public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_skip)
    TextView tv_skip;

    //=============该方法为抽象方法，强制复写，这里需要传入界面的布局id============//
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        // 如果上个界面通过bundle取值，可以在这里获取
        String value = bundle.getString("key");
        return super.initArgs(bundle);
    }

    @Override
    protected void initBefore() {
        super.initBefore();

        //=============标题栏============//

        // 设置标题文本
        getTitleBar().setTitle("登陆");
        // 设置标题为图片
        getTitleBar().setTitleImage(R.drawable.ic_launcher);
        // 标题栏 左上角的点击事件
        getTitleBar().setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        // 标题栏 右上角的文本内容、点击事件
        getTitleBar().addAction(new CommonTitleBar.TextAction("保存") {
            @Override
            public void performAction(View view) {
                // 这里是点击事件
            }
        });
         // 标题栏 右上角的图片内容、点击事件
        getTitleBar().addAction(new CommonTitleBar.ImageAction(R.drawable.ic_launcher) {
            @Override
            public void performAction(View view) {
                // 这里是点击事件
            }
        });

        // 如果不需要标题栏，可以隐藏
        hideTitleBar();

        //=============界面跳转============//
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 不传值的情况
                MainActivity.runActivity(mContext, MainActivity.class);

                // 2. 传值的情况
                Bundle bundle = new Bundle();
                bundle.putString("key", "value");
                MainActivity.runActivity(mContext, MainActivity.class, bundle);
            }
        });
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 这里可以初始化控件
    }

    @Override
    protected void initData() {
        super.initData();
        // 这里是获取网络数据的
    }
}
