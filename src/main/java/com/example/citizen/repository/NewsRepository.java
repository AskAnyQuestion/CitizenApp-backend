package com.example.citizen.repository;

import com.example.citizen.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Override
    List<News> findAll();
    @Override
    @SuppressWarnings("unchecked")
    News save(News news);
}
