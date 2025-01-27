package com.base.database.admin.util.service;

import com.base.database.admin.util.repository.AdminRepository;
import com.base.database.user.util.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn("adminRepository")
public class AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminService(AdminRepository adminRepository, ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
    }

//    public List<UserDto> findAllUsers() {
////        List<Contact> contacts = adminRepository.getContacts();
////        return contacts.stream()
////                .map(user -> modelMapper.map(user, UserDto.class))
////                .collect(Collectors.toList());
//        return null;
//    }

    public UserDto saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = adminRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public void deleteUser(Long id) {
        adminRepository.deleteById(Math.toIntExact(id));
    }
}

