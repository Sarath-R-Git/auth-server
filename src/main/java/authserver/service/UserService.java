package authserver.service;

import authserver.dto.User;
import authserver.exception.ResourceNotFoundException;
import authserver.mapper.interfaces.UserMapper;
import authserver.model.UserEntity;
import authserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() {
        //Need to implement specifiction<T>
        return userMapper.ListOfBtoA(userRepository.findAll());
    }

    public User createUser(User user) {
      try {
            return userMapper.BtoA(userRepository.save(userMapper.AtoB(user)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User updateUser(Long id, User user) {
        Optional<UserEntity> existingUserDetails = userRepository.findById(id);
        if(existingUserDetails.isEmpty()){
            throw new ResourceNotFoundException("USER_NOT_FOUND","User with provided id is not found.");
        }

        if(!existingUserDetails.get().getUsername().equalsIgnoreCase(user.getUsername())){
            throw new ResourceNotFoundException("USER_NOT_MODIFIED", "Username cannot be modified.");
        }else {
            user.setId(existingUserDetails.get().getId());
            return userMapper.BtoA(userRepository.save(userMapper.AtoB(user)));
        }

    }

    public void deleteUser(Long id) {

        Optional<UserEntity> existingUserDetails = userRepository.findById(id);
        if(existingUserDetails.isEmpty()){
            throw new ResourceNotFoundException("USER_NOT_FOUND","User with provided id is not found.");
        }
        userRepository.deleteById(id);
    }

    public User getByUserId(Long id) {
        Optional<UserEntity> existingUserDetails = userRepository.findById(id);
        if(existingUserDetails.isEmpty()){
            throw new ResourceNotFoundException("USER_NOT_FOUND","User with provided id is not found.");
        }
        return userMapper.BtoA(existingUserDetails.get());
    }
}
