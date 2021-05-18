package com.example.employee_management_system.repositories;

import com.example.employee_management_system.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
  //User findByEmailAndPassword(String email, String password);
  User findUserByEmailAndPassword(String email, String password);
  User findUserById(Long id);
  
  
}
