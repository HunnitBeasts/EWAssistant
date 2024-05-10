package com.hunnit_beasts.EWAssistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("main_page")
    public void mainPage(){}

    @GetMapping("test_page")
    public void testPage(){}

    @GetMapping("excel_page")
    public void excelPage(){}
}
