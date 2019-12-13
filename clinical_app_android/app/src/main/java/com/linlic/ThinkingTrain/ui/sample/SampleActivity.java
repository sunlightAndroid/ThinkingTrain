package com.linlic.ThinkingTrain.ui.sample;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.linlic.ThinkingTrain.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import me.sunlight.sdk.common.app.BaseActivity;
import me.sunlight.sdk.common.network.OkGoUtils;
import me.sunlight.sdk.common.network.callback.CommonStringCallback;
import me.sunlight.sdk.common.widget.EmptyView;

import static me.sunlight.sdk.common.network.OkGoUtils.post;

/**
 * <pre>
 *     author : eric
 *     time   : 2019/12/11
 *     desc   :
 *     version:
 * </pre>
 */
public class SampleActivity extends BaseActivity {

    // 第一步
    @BindView(R.id.emptyView)
    EmptyView mEmptyView;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    public static final String KEY = "KEY";

    private String key;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_sample;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        key = bundle.getString(KEY);
        return super.initArgs(bundle);
    }

    @Override
    protected void initBefore() {
        super.initBefore();

        getTitleBar().setTitle("sample");
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        // 第二步
        mEmptyView.bind(mRecyclerView);

        // 第三步 在需要显示空布局的地方使用

        mEmptyView.triggerOk(); // 显示列表 隐藏 空布局
        mEmptyView.triggerEmpty(); // 显示空布局 隐藏 列表

        mEmptyView.triggerOkOrEmpty(true); // 等同  mEmptyView.triggerOk();
        mEmptyView.triggerOkOrEmpty(false); // 等同   mEmptyView.triggerEmpty();

    }


    @Override
    protected void initData() {
        super.initData();
    }
}
