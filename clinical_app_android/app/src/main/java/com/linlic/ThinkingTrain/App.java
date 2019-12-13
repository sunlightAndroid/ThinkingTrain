package com.linlic.ThinkingTrain;

import android.content.Context;
import me.sunlight.sdk.common.app.BaseApplication;

/**
 * <pre>
 *     author : eric
 *     time   : 2019/12/09
 * </pre>
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void showAccountView(Context context) {
        super.showAccountView(context);
        //  Utils.cleanUp();
        // LoginActivity.runActivity(context, LoginActivity.class);
    }

}
