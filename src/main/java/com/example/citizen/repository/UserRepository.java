package com.example.citizen.repository;

import com.example.citizen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    /* Сохранение пользователя */
    @Override
    @SuppressWarnings("unchecked")
    User save(User user);

    /* Поиск пользователя по телефону */
    @Transactional
    @Query(value = "select p from User p where p.phone=:phone")
    User findUserByPhone(Long phone);

}
