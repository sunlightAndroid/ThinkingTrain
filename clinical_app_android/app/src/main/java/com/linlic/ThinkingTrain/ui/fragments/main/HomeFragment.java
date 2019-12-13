package com.linlic.ThinkingTrain.ui.fragments.main;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.linlic.ThinkingTrain.MainActivity;
import com.linlic.ThinkingTrain.R;
import com.linlic.ThinkingTrain.api.Urls;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import me.sunlight.sdk.common.app.BaseFragment;
import me.sunlight.sdk.common.network.OkGoUtils;
import me.sunlight.sdk.common.network.callback.CommonStringLoadingCallback;

/**
 * <pre>
 *     author : eric
 *     time   : 2019/12/11
 *     desc   : 首页
 *     version:
 * </pre>
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_home)
    TextView tv_home;

    public static Fragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        ((MainActivity) mContext).switchTitle(0);
        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject object = new JSONObject();
                try {
                    object.put("act", Urls.getWenlist);
                    object.put("uid", "10074011");
                    object.put("case_bank_id", "144");
                    object.put("exam_id", "51");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Logger.t("首页数据").e(object.toString());
                OkGoUtils.post(Urls.baseUrl, object.toString(), new CommonStringLoadingCallback() {
                    @Override
                    protected void onSuccess(String result) {
                        tv_home.setText(result);
                    }

                });
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

    }
}
