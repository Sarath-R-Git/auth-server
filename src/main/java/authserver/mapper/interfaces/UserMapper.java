package authserver.mapper.interfaces;

import authserver.dto.User;
import authserver.mapper.decorators.UserDecorator;
import authserver.model.UserEntity;
import org.mapstruct.Builder;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@DecoratedWith(UserDecorator.class)
public interface UserMapper {

    UserEntity AtoB(User user);

    User BtoA(UserEntity userEntity);

    List<UserEntity> ListOfAtoB(List<User> users);

    List<User> ListOfBtoA(List<UserEntity> usersEntity);
}
