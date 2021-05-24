package com.example.employee_management_system.service;

import java.util.List;

import com.example.employee_management_system.model.Attendance;
import com.example.employee_management_system.model.User;

public interface UserService {
  User getUserByEmailAndPassword(String email, String password);

  void saveEmployee(User employee);

  List<User> getAllUser();

  User getUserById(Long id);

  void updateEmployee(User user);

  void delete(User employee);
  
}
