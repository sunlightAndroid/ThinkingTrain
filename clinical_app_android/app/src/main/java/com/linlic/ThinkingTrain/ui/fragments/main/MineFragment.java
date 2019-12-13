package com.linlic.ThinkingTrain.ui.fragments.main;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.linlic.ThinkingTrain.MainActivity;
import com.linlic.ThinkingTrain.R;

import me.sunlight.sdk.common.app.BaseFragment;

/**
 * <pre>
 *     author : eric
 *     time   : 2019/12/11
 *     desc   : 我的
 *     version:
 * </pre>
 */
public class MineFragment extends BaseFragment {

    public static Fragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        ((MainActivity)mContext).switchTitle(2);
    }
}
