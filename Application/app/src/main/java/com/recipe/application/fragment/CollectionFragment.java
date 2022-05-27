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
import com.recipe.application.activity.ShowActivity;
import com.recipe.application.adapter.SortListAdapter;
import com.recipe.application.dao.Like;
import com.recipe.application.dao.Post;
import com.recipe.application.dao.User;

import java.util.ArrayList;
import java.util.List;



public class CollectionFragment extends Fragment {

    private ListView f_collection;
    private SortListAdapter sortListAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_collection,null,false);
        f_collection=view.findViewById(R.id.f_collection);
        showCollection();
        return view;
    }

    private void showCollection(){
       Like like=new Like();
             List list=new ArrayList();
                    List<String> o=new ArrayList<>();

                        String sequence=like.getPostId().toString();
                        o.add(sequence);

                            Post post=new Post();
                                sortListAdapter=new SortListAdapter(getContext(),list);
                                f_collection.setAdapter(sortListAdapter);
                                f_collection.setOnItemClickListener((parent, view, position, id) -> {
                                    String objectId=post.toString();
                                    Intent intent=new Intent(getContext(), ShowActivity.class);
                                    intent.putExtra("objectId",objectId);
                                    startActivity(intent);
                                });
                            }
                        }



