package com.example.citizen.repository;

import com.example.citizen.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    /* Сохранить уведомление */
    @Override
    @SuppressWarnings("unchecked")
    Notification save(Notification notification);
    /* Получить уведомление по id */
    @Transactional
    @Query(value = "select p from Notification p where p.idNotification=:idNotification")
    Notification getNotificationById(int idNotification);
}
