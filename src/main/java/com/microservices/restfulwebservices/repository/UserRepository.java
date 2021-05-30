package com.microservices.restfulwebservices.repository;

import com.microservices.restfulwebservices.repository.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    @Query(value = "SELECT * FROM users WHERE NAME = ?1",
//    countQuery = "SELECT count(*) FROM users WHERE NAME = ?1",
//    nativeQuery = true)
//    Page<User> findbyName(Pageable pageable, String name);
//
//    List<User> findByUserId(Integer userId);
//
//    Sort sort = Sort.by("name").ascending()
//                    .and(Sort.by("DateBirth").descending());
}
