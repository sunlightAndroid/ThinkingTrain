package me.sunlight.sdk.common.app.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import me.sunlight.sdk.common.app.BaseFragment;
import me.sunlight.sdk.common.app.presenter.BaseContract;
import me.sunlight.sdk.common.widget.toast.UIToast;

/**
 * Created by eric on 2019/12/12.
 * 邮箱：1944633835@qq.com
 */
public abstract class PresenterFragment<Presenter extends BaseContract.Presenter> extends BaseFragment
        implements BaseContract.View<Presenter> {

    public abstract Presenter initPresenter();

    protected  Presenter mPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError(String str) {
        UIToast.showMessage(str);
    }


    @Override
    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null)
            mPresenter.destroy();

    }
}


