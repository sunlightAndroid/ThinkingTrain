package com.linlic.ThinkingTrain.ui.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.linlic.ThinkingTrain.R;

import butterknife.BindView;
import me.sunlight.sdk.common.app.BaseFragment;

/**
 * <pre>
 *     author : eric
 *     time   : 2019/12/10
 *     desc   :
 *     version:
 * </pre>
 */
public class SampleFragment extends BaseFragment {

    @BindView(R.id.tv_sample)
    TextView tv_sample;

    // 布局的资源id
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_sample;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        // 这里如果 Activity 有通过bundle传值过来，那么就可以在这里取值了
        String value = bundle.getString("key");
        super.initArgs(bundle);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        // 这里可以做些初始化的操作 比如 RecyclerView

    }

    @Override
    protected void initData() {
        super.initData();
        // 可以做 拉取网络数据的操作

        // pullData1();
        // pullData2();
        //  mPlaceHolderView.triggerOkOrEmpty();
    }
}
