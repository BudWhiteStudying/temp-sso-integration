package com.budwhite.studying.externalssodemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thyme")
public class TemplateController {

    @GetMapping(value = "/derp")
    public String getIndex() {
        return "index";
    }

    @PostMapping(value = "/derp")
    public String postIndex() {
        return "index";
    }
}
