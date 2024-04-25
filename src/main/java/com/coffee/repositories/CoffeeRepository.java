package com.coffee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffee.models.Menu;

public interface CoffeeRepository extends JpaRepository<Menu, Integer>{

}
