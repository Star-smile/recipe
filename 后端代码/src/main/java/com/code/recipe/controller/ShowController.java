package com.code.recipe.controller;

import com.code.recipe.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShowController {
    @Autowired
    ShowService service;

}
