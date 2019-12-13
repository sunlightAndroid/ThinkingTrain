package me.sunlight.sdk.common.app.mvp;


import androidx.annotation.LayoutRes;

import me.sunlight.sdk.common.app.BaseActivity;
import me.sunlight.sdk.common.app.presenter.BaseContract;
import me.sunlight.sdk.common.widget.toast.UIToast;

/**
 * Created by eric on 2019/12/12.
 * 邮箱：1944633835@qq.com
 */
public abstract class PresenterActivity<Presenter extends BaseContract.Presenter> extends BaseActivity
        implements BaseContract.View<Presenter> {


    public abstract Presenter initPresenter();

    protected Presenter mPresenter;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
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
        mPresenter =presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       if(mPresenter!=null)
           mPresenter.destroy();
    }
}
