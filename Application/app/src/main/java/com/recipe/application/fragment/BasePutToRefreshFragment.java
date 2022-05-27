package com.recipe.application.fragment;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.recipe.application.R;
import com.recipe.application.utils.TDevice;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

public abstract class BasePutToRefreshFragment<T extends BaseAdapter> extends Fragment {

    //下拉刷新
    public static final int REFRESH_TYPE_PULL = 0;
    //上拉加载更多
    public static final int REFRESH_TYPE_LOAD_MORE = 1;

    //上拉加载的各种状态
    public static final int PULL_UP_STATE_NONE = 0;
    public static final int PULL_UP_STATE_LOADMORE = 1;
    public static final int PULL_UP_STATE_NOMORE = 2;
    public static final int PULL_UP_STATE_ERROR = 3;
    protected int sPutUpState = PULL_UP_STATE_NONE;
    //分页--目前的页码
    protected int mCurrentPageCount = 0;
    //FootView的进度圈
    protected ProgressBar mFootViewProgressBar;
    //FootViewTextView
    protected TextView mFootViewText;

    private View mRootView;
    protected ListView mListView;
    protected T mAdapter;
    protected LinearLayout mFooterView;
    protected PtrFrameLayout mPtrFrameLayout;

    //list的数量是否满屏
    private boolean isItemFullScreen = false;

    //继承的Fragment是否支持上拉加载更多
    private boolean isLoadMore = false;

    //返回根布局的View
    public abstract View getRootView();

    //返回Adapter的实现
    public abstract T getAdapter();

    //处理读缓存逻辑
    public abstract void readCache();

    //从网络请求数据 actionType 表示 上拉类型 还是下拉类型 操作
    public abstract void requestDataByNet(int actionType);

    //初始化参数
    public abstract void initArguments();

    //创建View或者加入事件监听
    public abstract void createViewsOrListener();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mRootView == null) {
            mRootView = getRootView();
            createBaseViews();
        }
        //缓存的mRootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个mRootView已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    //创建listView loadingView并加入事件处理
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createBaseViews() {
        initDoRefreshView();
        mListView = mRootView.findViewById(R.id.listview);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (isItemFullScreen) {
                        if (sPutUpState == PULL_UP_STATE_NONE && isLoadMore) {
                            pullUpLoadData();
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isItemFullScreen= totalItemCount > visibleItemCount;
            }
        });
        mAdapter=getAdapter();
        readCache();
        mListView.setAdapter(mAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initDoRefreshView(){
        mPtrFrameLayout= mRootView.findViewById(R.id.layout_refresh);
        MaterialHeader header=new MaterialHeader((getContext()));
        int[] colors=getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1,-2));
        header.setPadding(0,(int) TDevice.dpToPixel(15),0,(int)TDevice.dpToPixel(10));
        header.setPtrFrameLayout(mPtrFrameLayout);

        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setDurationToCloseHeader(1500);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);
        mPtrFrameLayout.setPinContent(true);
        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,mListView,header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if(frame.isAutoRefresh()){
                    if(mPtrFrameLayout.isRefreshing()){
                        mPtrFrameLayout.refreshComplete();
                    }
                }
                sPutUpState=PULL_UP_STATE_NONE;
                requestDataByNet(REFRESH_TYPE_PULL);
            }
        });
    }

    public void pullUpLoadData() {
        sPutUpState = PULL_UP_STATE_LOADMORE;
        showFootView();
        requestDataByNet(REFRESH_TYPE_LOAD_MORE);
    }

    public void removeFootView() {
        if (mFooterView != null) {
            mListView.removeFooterView(mFooterView);
        }
        sPutUpState = PULL_UP_STATE_NONE;
    }

    public void showFootView(){
        if (mFooterView == null) {
            assert mFooterView != null;
            mFootViewText = mFooterView.findViewById(R.id.text);
            //mFootViewProgressBar = mFooterView.findViewById(R.id.progressbar);
            mFootViewText = mFooterView.findViewById(R.id.text);
        }
        updateFooterViewStateText();
        if (mListView.getFooterViewsCount() == 0) {
            mListView.addFooterView(mFooterView);
        }
    }

    public void updateFooterViewStateText(){
        switch(sPutUpState){
            case PULL_UP_STATE_LOADMORE:
                mFootViewProgressBar.setVisibility(View.VISIBLE);
                mFootViewText.setText(R.string.loading);
                break;
            case PULL_UP_STATE_NOMORE:
                mFootViewProgressBar.setVisibility(View.GONE);
                mFootViewText.setVisibility(View.VISIBLE);
                mFootViewText.setText(R.string.all_load);
                break;
            case PULL_UP_STATE_ERROR:
                mFootViewProgressBar.setVisibility(View.GONE);
                mFootViewText.setVisibility(View.VISIBLE);
                mFootViewText.setText(R.string.error_load);
                break;
        }
    }

    public void setLoadMore(boolean isLoadMore){
        this.isLoadMore=isLoadMore;
    }


}
