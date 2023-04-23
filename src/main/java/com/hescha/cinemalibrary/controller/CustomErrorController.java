package com.hescha.cinemalibrary.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(path = "/error", produces = "text/html")
    public String handleError() {
        return "404";
    }
}
