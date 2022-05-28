package com.recipe.application.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.recipe.application.R;
import com.recipe.application.activity.ShowActivity;
import com.recipe.application.activity.SortActivity;
import com.recipe.application.adapter.SortListAdapter;
import com.recipe.application.dao.Brief_dish;
import com.recipe.application.dao.Post;
import com.recipe.application.utils.BitmapUtils;
import com.recipe.application.utils.CameraUtils;
import com.recipe.application.utils.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;
import butterknife.ButterKnife;


public class MsgFragment extends Fragment {
    //图片控件
    private ShapeableImageView ivHead;
    //Base64
    private String base64Pic;
    //拍照和相册获取图片的Bitmap
    private Bitmap orc_bitmap;

    private ListView list;
    private SortListAdapter adapter;
    //判断用户点击了那个标签
    private String name;

    private SearchView searchView;

    //Glide请求图片选项配置
    private RequestOptions requestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);//不做内存缓存

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg_content, container, false);
        ButterKnife.bind(this, view);

        ivHead=view.findViewById(R.id.iv_head);
        list=view.findViewById(R.id.list);
        searchView=view.findViewById(R.id.searchView);


        TextView sort=view.findViewById(R.id.sort);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), SortActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getArguments();
        name = bundle.getString("name");
        if (name == null) {
            name = "参数非法";
        }

        String path=SPUtils.getString("imageUrl","",getContext());
        displayImage(path);
        putToRefreshByUnit();

        return view;
    }

    /**
     * 通过图片路径显示图片
     */
    private void displayImage(String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {

            //显示图片
            Glide.with(this).load(imagePath).apply(requestOptions).into(ivHead);

            //压缩图片
            orc_bitmap = CameraUtils.compression(BitmapFactory.decodeFile(imagePath));

            //转Base64
            base64Pic = BitmapUtils.bitmapToBase64(orc_bitmap);

        }
    }

    /**
     * “单元”列表下拉刷新具体实现
     */
    private void putToRefreshByUnit() {



    }
}
