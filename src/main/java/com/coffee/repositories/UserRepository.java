package com.coffee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffee.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
