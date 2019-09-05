package tjs.ax.admin.dto.do2dto;


import org.mapstruct.factory.Mappers;
import tjs.ax.admin.domain.User;
import tjs.ax.admin.dto.UserDto;

import java.util.List;


@org.mapstruct.Mapper
public interface UserConvert {
    UserConvert MAPPER = Mappers.getMapper(UserConvert.class);

    public UserDto do2dto(User person);

    public List<UserDto> dos2dtos(List<User> list);
}
