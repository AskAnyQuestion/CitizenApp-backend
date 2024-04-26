package com.example.citizen.repository;

import com.example.citizen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    /* Сохранение пользователя */
    @Override
    @SuppressWarnings("unchecked")
    User save(User user);

    /* Поиск пользователя по юзернейму */
    @Transactional
    Optional<User> findByPhone(Long phone);

}
