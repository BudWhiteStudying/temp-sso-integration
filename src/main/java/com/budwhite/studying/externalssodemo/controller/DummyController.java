package com.budwhite.studying.externalssodemo.controller;

import com.budwhite.studying.externalssodemo.config.UserInformation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
public class DummyController {
    @GetMapping(value = "/daje")
    public String theDummy() {
        return "pippo";
    }

    @GetMapping(value = "/user-info")
    public String getUserInfo() {
        return ((UserInformation)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUserFullName();
    }

    @PostMapping("/logout")
    public boolean logout() {
        SecurityContextHolder.clearContext();
        return true;
    }
}
