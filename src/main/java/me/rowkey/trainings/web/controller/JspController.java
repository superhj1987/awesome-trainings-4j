package me.rowkey.trainings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: Bryant.Hang
 * Date: 2018/3/30
 * Email: superhj1987@126.com
 */
@Controller
@RequestMapping("/jsp")
public class JspController {
    @GetMapping("")
    public String test(ModelMap modelMap) {
        modelMap.addAttribute("name", "aadas");
        return "index";
    }
}
