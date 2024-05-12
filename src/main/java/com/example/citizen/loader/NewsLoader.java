package com.example.citizen.loader;

import com.example.citizen.model.News;
import com.example.citizen.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class NewsLoader implements CommandLineRunner {
    @Autowired
    private NewsService newsService;

    public NewsLoader(NewsService newsService) {
        this.newsService = newsService;
    }
    @Override
    public void run(String... args) {
        News news = new News();
        news.setIdNews(1);
        news.setLatitude(57.216746);
        news.setLongitude(41.932818);
        news.setEventTime(new Timestamp(System.currentTimeMillis()));
        news.setDescription("6 вооружённых людей устроили стрельбу возле автовокзала");
        news.setAddition("Медицинский персонал подтверждает, что общее число людей 6");
        newsService.save(news);
    }
}
