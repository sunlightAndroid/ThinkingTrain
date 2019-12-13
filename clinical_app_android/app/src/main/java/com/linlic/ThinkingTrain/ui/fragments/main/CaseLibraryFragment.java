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
 *     desc   : 病例库
 *     version:
 * </pre>
 */
public class CaseLibraryFragment extends BaseFragment {

    public static Fragment newInstance() {
        CaseLibraryFragment fragment = new CaseLibraryFragment();
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_case_library;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        ((MainActivity)mContext).switchTitle(1);
    }
}
