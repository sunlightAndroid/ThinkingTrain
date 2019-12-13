package com.linlic.ThinkingTrain;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.linlic.ThinkingTrain.ui.activities.account.RegisterActivity;
import com.linlic.ThinkingTrain.ui.fragments.main.CaseLibraryFragment;
import com.linlic.ThinkingTrain.ui.fragments.main.HomeFragment;
import com.linlic.ThinkingTrain.ui.fragments.main.MineFragment;
import com.linlic.ThinkingTrain.ui.sample.SampleActivity;
import com.yalantis.ucrop.UCrop;

import butterknife.BindView;
import me.sunlight.sdk.common.app.BaseActivity;
import me.sunlight.sdk.common.util.Utils;
import me.sunlight.sdk.common.widget.bottombar.tab.BottomTab;
import me.sunlight.sdk.common.widget.bottombar.tab.TabModel;
import me.sunlight.sdk.common.widget.titlebar.CommonTitleBar;
import me.sunlight.sdk.common.widget.toast.UIToast;


public class MainActivity extends BaseActivity {

    @BindView(R.id.bottomTab)
    BottomTab mBottomtab;
    @BindView(R.id.home_frags_container)
    FrameLayout mContainer;

    private Fragment mCurrentFragment;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initBefore() {
        super.initBefore();
        // 让标题栏左边的返回icon隐藏
        getTitleBar().setLeftVisible(false);
        getTitleBar().addAction(new CommonTitleBar.TextAction("跳过") {
            @Override
            public void performAction(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(SampleActivity.KEY, "传个值过去");
                SampleActivity.runActivity(mContext, SampleActivity.class, bundle);
            }
        });

        // 测试数据
        Utils.saveAccount("xh1");
        Utils.savePassword("123456");
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mBottomtab.initTab(R.color.black_alpha_48, R.color.black);
        mBottomtab
                .addTab(new TabModel.Builder()
                        .addTabText(getResources().getString(R.string.label_tab_home))
                        .addTabNormalIcon(R.mipmap.ic_top_normal)
                        .addTabSelectedIcon(R.mipmap.ic_top_pressed)
                        .build())
                .addTab(new TabModel.Builder()
                        .addTabText(getResources().getString(R.string.label_tab_caselibrary))
                        .addTabNormalIcon(R.mipmap.ic_mission_normal)
                        .addTabSelectedIcon(R.mipmap.ic_mission_pressed)
                        .build())

                .addTab(new TabModel.Builder()
                        .addTabText(getResources().getString(R.string.label_tab_mine))
                        .addTabNormalIcon(R.mipmap.ic_top_normal)
                        .addTabSelectedIcon(R.mipmap.ic_top_pressed)
                        .build())
                .setOnTabItemClickListener(new BottomTab.onTabItemClickListener() {
                    @Override
                    public void onTabItemClick(int position) {
                        switch (position) {
                            case 0:
                                if (mCurrentFragment instanceof HomeFragment) {
                                    return;
                                }
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.home_frags_container, mCurrentFragment = HomeFragment.newInstance())
                                        .commit();
                                break;

                            case 1:
                                if (mCurrentFragment instanceof CaseLibraryFragment) {
                                    return;
                                }
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.home_frags_container, mCurrentFragment = CaseLibraryFragment.newInstance())
                                        .commit();
                                break;

                            case 2:
                                if (mCurrentFragment instanceof MineFragment) {
                                    return;
                                }
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.home_frags_container, mCurrentFragment = MineFragment.newInstance())
                                        .commit();
                                break;
                        }
                    }
                });
        // 第一次默认加载首页
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_frags_container, mCurrentFragment = HomeFragment.newInstance())
                .commit();
    }


    public void switchTitle(int position) {
        switch (position) {
            case 0:
                getTitleBar().setTitle("首页");
                break;

            case 1:
                getTitleBar().setTitle("病例库");
                break;

            case 2:
                getTitleBar().setTitle("我的");
                break;
        }
    }
}
