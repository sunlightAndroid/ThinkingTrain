package com.linlic.ThinkingTrain.factory.Contracts;

import me.sunlight.sdk.common.app.presenter.BasePresenter;

/**
 * <pre>
 *     author : eric
 *     time   : 2019/12/08
 *     desc   :
 *     version:
 * </pre>
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {


    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void doRegister(String account, String password) {
        // 注册

        // 模拟 提交数据到服务器

        // 然后通知View层 是否成功
        getView().RegisterSuccess();

    }
}
