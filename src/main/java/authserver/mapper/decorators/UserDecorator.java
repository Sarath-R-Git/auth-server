package authserver.mapper.decorators;

import authserver.dto.User;
import authserver.mapper.interfaces.UserMapper;
import authserver.model.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class UserDecorator implements UserMapper {

    protected UserMapper delegate;

    @Autowired
    public void setDelegate(UserMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public UserEntity AtoB(User user) {
        UserEntity userEntity = delegate.AtoB(user);
        afterMappingToB(userEntity, user);
        return userEntity;
    }

    @Override
    public User BtoA(UserEntity userEntity) {
        User user = delegate.BtoA(userEntity);
        afterMappingToA(user, userEntity);
        return user;
    }

    @Override
    public List<UserEntity> ListOfAtoB(List<User> users) {
        List<UserEntity> entityList = delegate.ListOfAtoB(users);
        for (int i = 0; i < entityList.size(); i++) {
            afterMappingToB(entityList.get(i), users.get(i));
        }
        return entityList;
    }

    @Override
    public List<User> ListOfBtoA(List<UserEntity> usersEntity) {
        List<User> dtoList = delegate.ListOfBtoA(usersEntity);
        for (int i = 0; i < dtoList.size(); i++) {
            afterMappingToA(dtoList.get(i), usersEntity.get(i));
        }
        return dtoList;
    }

    @AfterMapping
    protected void afterMappingToA(@MappingTarget User dto, UserEntity entity) {

    }

    @AfterMapping
    protected void afterMappingToB(@MappingTarget UserEntity entity, User dto) {

    }
}
