package com.linlic.ThinkingTrain.factory.Contracts;

import me.sunlight.sdk.common.app.presenter.BaseContract;

/**
 * <pre>
 *     author : eric
 *     time   : 2019/12/08
 *     desc   :
 *     version:
 * </pre>
 */
public interface RegisterContract  {

    interface View extends BaseContract.View<Presenter> {
        void RegisterSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        void doRegister(String account,String password);
    }

}
