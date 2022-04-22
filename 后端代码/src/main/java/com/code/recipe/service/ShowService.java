package com.code.recipe.service;

import com.code.recipe.dao.ShowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService {

    @Autowired
    ShowMapper mapper;
}
