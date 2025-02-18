package authserver.service;

import authserver.dto.User;
import authserver.mapper.interfaces.UserMapper;
import authserver.model.UserEntity;
import authserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userMapper.BtoA(userRepository.save(userMapper.AtoB(user)));
    }
}
