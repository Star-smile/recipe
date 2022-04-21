package com.code.recipe.service;

import com.code.recipe.dao.UserEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserEventService {
    @Autowired
    UserEventMapper mapper;

    public boolean addFocusMsgService(String name,String who){
        String focus_id= UUID.randomUUID().toString();
        int r=mapper.addFocusMsgMapper(focus_id,name,who);
        return r==1;
    }
}
