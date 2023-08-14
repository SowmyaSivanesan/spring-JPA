  package com.example.demo.jparepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.JPAModel;

@Repository
public interface JPARepository extends JpaRepository<JPAModel, String> {

	
}
