package com.code.recipe.service;

import com.code.recipe.bean.NavBean;
import com.code.recipe.bean.TypeBean;
import com.code.recipe.dao.ShowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowMapper mapper;

    public List<TypeBean> getTypeMsgService(){
        return mapper.getTypeMsgMapper();
    }

    public List<NavBean> getNavMsgService(){
        return mapper.getNavMsgMapper();
    }
}
