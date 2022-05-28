package com.recipe.application.activity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipe.application.R;
import com.recipe.application.adapter.MainFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrimaryActivity extends AppCompatActivity {

    /**
     * 菜单标题
     */
    private final int[] TAB_TITLES = new int[]{R.string.menu_shouye, R.string.menu_dongtai, R.string.menu_my};
    /**
     * 菜单图标
     */
    private final int[] TAB_IMGS = new int[]{R.drawable.tab_main_msg_selector, R.drawable.tab_main_find_selector
            , R.drawable.tab_main_me_selector};

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    /**
     * 页卡适配器
     */
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        ButterKnife.bind(this);

        // 初始化页卡
        initPager();

        setTabs(tabLayout, getLayoutInflater(), TAB_TITLES, TAB_IMGS);
    }

    /**
     * 设置页卡显示效果
     * @param tabLayout
     * @param inflater
     * @param tabTitlees
     * @param tabImgs
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] tabTitlees, int[] tabImgs) {
        for (int i = 0; i < tabImgs.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.item_main_menu, null);
            // 使用自定义视图，目的是为了便于修改，也可使用自带的视图
            tab.setCustomView(view);

            TextView tvTitle = (TextView) view.findViewById(R.id.txt_tab);
            tvTitle.setText(tabTitlees[i]);
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);
            tabLayout.addTab(tab);
        }
    }

    private void initPager() {
        adapter = new MainFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        // 关联切换
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 取消平滑切换
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}