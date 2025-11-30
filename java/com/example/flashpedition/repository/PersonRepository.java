package com.example.flashpedition.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flashpedition.entity.Person;
public interface PersonRepository extends JpaRepository<Person, Long>{
	Optional<Person> findByEmail(String email);
}
