package com.code.recipe.service;

import com.code.recipe.dao.UserEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEventService {
    @Autowired
    UserEventMapper mapper;
}
