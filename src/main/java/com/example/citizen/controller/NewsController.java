package com.example.citizen.controller;

import com.example.citizen.model.News;
import com.example.citizen.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;
    @GetMapping("/get")
    @ResponseBody
    public List<News> get() {
        return newsService.getNews();
    }

    @GetMapping("/all")
    @ResponseBody
    public Resource getAllNews() throws Exception {
        return newsService.getMaterials();
    }
}
