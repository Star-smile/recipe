package com.recipe.application.fragment;

import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.recipe.application.R;
import com.recipe.application.activity.PersonalActivity;
import com.recipe.application.adapter.WorkAdapter;
import com.recipe.application.dao.Focus;
import com.recipe.application.dao.User;

import java.util.ArrayList;
import java.util.List;


public class WorkFragment extends Fragment {
    private ListView f_work;
    private WorkAdapter workAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container, false);
        f_work=view.findViewById(R.id.f_work);

        showRecipe();
        return view;
    }

    private void showRecipe(){

        final User user=new User();
        String who=user.getName();
        List<String> names=new ArrayList<>();

        f_work.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final User user1=new User();
                Intent intent=new Intent(getContext(), PersonalActivity.class);
                startActivity(intent);
            }
        });

            }

    }


