package com.example.citizen.repository;

import com.example.citizen.model.Notification;
import com.example.citizen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /* Сохранение пользователя */
    @Override
    @SuppressWarnings("unchecked")
    User save(User user);

    /* Поиск пользователя по телефону */
    @Transactional
    @Query(value = "select p from User p where p.phone=:phone")
    User findUserByPhone(Long phone);

    /* Поиск id по логину */
    @Transactional
    @Query(value = "select p from User p where p.login=:login and p.phone=:phone")
    User findUser(String login, Long phone);

    /* Обновление токена */
    @Modifying
    @Transactional
    @Query(value = "update User u set u.token=:token where u.id=:id")
    void updateUserByToken(Integer id, String token);

    /* Обновить список получаемых происшествий у пользователя */
    /*@Modifying
    @Transactional
    @Query(value = "update User u set u.notifications=:notifications where u.id=:id")
    void updateUserById(@Param("id") Integer id, @Param("notifications") List<Notification> notifications);*/

    /* Список токенов */
    @Transactional
    @Query(value = "select p.token from User p where p.idUser <> :idUser")
    List<String> findTokens(int idUser);

    /* Список токенов */
    @Transactional
    @Query(value = "select p from User p where p.token=:token")
    User getUserByToken(String token);

    /* Поиск пользователя по id */
    @Transactional
    @Query(value = "select p from User p where p.idUser=:idUser")
    User getUserById(int idUser);
}
