package com.dcn.simpleresource.domain.mapper;

import com.dcn.simpleresource.domain.dto.UserDto;
import com.dcn.simpleresource.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDto, UserEntity> {

    @Mapping(target = "username", expression = "java(usernameConverter(entity.getUsername()))")
    UserDto toDto(UserEntity entity);

    @Named("usernameConverter")
    default String usernameConverter(String name) {
        return name == null ? null : name.toUpperCase();
    }
}
