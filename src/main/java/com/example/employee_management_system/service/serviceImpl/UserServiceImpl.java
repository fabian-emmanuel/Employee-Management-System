package com.example.employee_management_system.service.serviceImpl;

import java.util.List;

import com.example.employee_management_system.model.Attendance;
import com.example.employee_management_system.model.User;
import com.example.employee_management_system.repositories.UserRepo;
import com.example.employee_management_system.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.OrderBy;

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
  @OrderBy("id desc")
  public List<User> getAllUser() {
    return userRepo.findAll();
  }

  @Override
  public User getUserById(Long id) {
    return userRepo.findUserById(id);
  }

  @Override
  public void updateEmployee(User employee) {
    userRepo.save(employee);
  }

  @Override
  public void delete(User employee) {
    userRepo.delete(employee);
  }

}
