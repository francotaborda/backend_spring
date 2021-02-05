package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {
//public interface IUserRepository extends CrudRepository<User, String> {

//    @Query("select u from users u where u.firstname like :nombre order by u.firstname")
//    List<User> buscaPorNombre(@Param("nombre") String nombre);

//    List<User> findIsLikeNombreOrderByNombre(String nombre);

    User findByEmail(String email);

}
