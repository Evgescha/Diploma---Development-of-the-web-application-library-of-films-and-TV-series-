package com.hescha.cinemalibrary.controller;

import com.hescha.cinemalibrary.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {
    private final ItemService service;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("list", service.readAll());
        model.addAttribute("topList", service.readTop3Commented());
        return "index";
    }

    @GetMapping("/about")
    public String getPageabout() {
        return "about";
    }

    @GetMapping("/pricing")
    public String pricing() {
        return "pricing";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/404")
    public String getPage404() {
        return "404";
    }

    @GetMapping("/catalog")
    public String catalog() {
        return "catalog";
    }

    @GetMapping("/details")
    public String details() {
        return "details";
    }
}
