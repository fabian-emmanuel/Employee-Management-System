package com.example.employee_management_system.service.serviceImpl;

import java.util.List;

import com.example.employee_management_system.model.User;
import com.example.employee_management_system.repositories.UserRepo;
import com.example.employee_management_system.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
  @Autowired UserRepo userRepo;

  @Override
  public User getUserByEmailAndPassword(String email, String password) {
    return userRepo.findUserByEmailAndPassword(email, password);
  }

  @Override
  public void saveEmployee(User employee) {
    userRepo.save(employee);
  }

  @Override
  public List<User> getAllUser() {
    return userRepo.findAll();
  }

  @Override
  public User getUserById(Long id) {
    return userRepo.findUserById(id);
    
  }

  @Override
  public void updateEmployee(User employee, Long id) {
    User _employee = userRepo.findUserById(id);
    _employee.setFirstName(employee.getFirstName());
    _employee.setLastName(employee.getLastName());
    _employee.setEmail(employee.getEmail());
    userRepo.save(employee);
    
  }

  @Override
  public void delete(User employee) {
    userRepo.delete(employee);
  }
  
}
