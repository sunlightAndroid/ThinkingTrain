package com.linlic.ThinkingTrain.ui.activities.account;

import android.widget.EditText;

import com.linlic.ThinkingTrain.R;
import com.linlic.ThinkingTrain.factory.Contracts.RegisterContract;
import com.linlic.ThinkingTrain.factory.Contracts.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import me.sunlight.sdk.common.app.mvp.PresenterActivity;
import me.sunlight.sdk.common.widget.toast.UIToast;

public class RegisterActivity extends PresenterActivity<RegisterContract.Presenter>
        implements RegisterContract.View {

    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_password)
    EditText mEtPassword;

    @Override
    protected void initBefore() {
        super.initBefore();
        getTitleBar().setTitle("注册");
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void RegisterSuccess() {
        UIToast.showMessage("Presenter已经处理逻辑，注册成功～～");
    }

    @Override
    public RegisterContract.Presenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        String account = mEtAccount.getText().toString();
        String password = mEtPassword.getText().toString();
        mPresenter.doRegister(account,password);
    }

}
