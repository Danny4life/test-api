package com.osiki.testproject.service.impl;

import com.osiki.testproject.entity.UserEntity;
import com.osiki.testproject.exception.ValidationException;
import com.osiki.testproject.model.Users;
import com.osiki.testproject.repository.UserRepository;
import com.osiki.testproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Users saveUser(Users users) {

//        if(users.getAgreeToTerms() == null || users.getAgreeToTerms().isEmpty()){
//            throw new ValidationException("Agree to terms");
//        }

        UserEntity userEntity = UserEntity.builder()
                .id(users.getId())
                .name(users.getName())
                .sectors(users.getSectors())
                .agreeToTerms(users.getAgreeToTerms())
                .build();

        userRepository.save(userEntity);

        return users;
    }

    @Override
    public List<Users> getAllUsers() {
        List<UserEntity> userEntities =userRepository.findAll();
        List<Users> users = userEntities
                .stream()
                .map(user -> new Users(user.getId(), user.getName(), user.getSectors(), user.getAgreeToTerms()))
                .toList();


        return users;
    }

    @Override
    public Users getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        Users users = new Users();
        BeanUtils.copyProperties(userEntity, users);

        return users;
    }

    @Override
    public Users updateUsers(Long id, Users users) {
        UserEntity userEntity = userRepository.findById(id).get();

        userEntity.setName(users.getName());
        userEntity.setSectors(users.getSectors());
        userEntity.setAgreeToTerms(userEntity.getAgreeToTerms());

        userRepository.save(userEntity);

        return users;
    }

    @Override
    public boolean deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();

        userRepository.delete(userEntity);

        return true;
    }
}
