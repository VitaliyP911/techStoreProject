package com.epam.dto.mapper;

import com.epam.dto.UserDto;
import com.epam.entity.Entity;
import com.epam.entity.User;

public class UserDtoMapper implements DtoMapper<UserDto>{
    @Override
    public UserDto mapFromEntityToDto(Entity entity) {
        User user = (User) entity;
        return new UserDto(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
    }
}
