package com.recipe.application.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.recipe.application.R;
import com.recipe.application.activity.PersonalActivity;
import com.recipe.application.adapter.FanAdapter;
import com.recipe.application.dao.Focus;
import com.recipe.application.dao.User;

import java.util.ArrayList;
import java.util.List;



public class FanFragment extends Fragment {

    private ListView f_fan;
    private FanAdapter fanAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fan, container, false);
        f_fan=view.findViewById(R.id.f_fan);
        showFan();
        return view;
    }

    private void showFan(){
        User user=new User();
        String who=user.getName();
    List list=new ArrayList();
                    List<String> names=new ArrayList<>();
                    Focus focus=new Focus();
                        String n=focus.getWho();
                        names.add(n);


                                fanAdapter=new FanAdapter(getContext(),list);
                                f_fan.setAdapter(fanAdapter);
                                f_fan.setOnItemClickListener((parent, view, position, id) -> {
                                    String objectId=user.toString();
                                    Intent intent=new Intent(getContext(), PersonalActivity.class);
                                    intent.putExtra("objectId",objectId);
                                    startActivity(intent);
                                });
                            }
                        }
