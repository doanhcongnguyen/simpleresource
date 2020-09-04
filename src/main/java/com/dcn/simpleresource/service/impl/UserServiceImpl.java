package com.dcn.simpleresource.service.impl;

import com.dcn.simpleresource.domain.dto.PaginationDto;
import com.dcn.simpleresource.domain.dto.UserDto;
import com.dcn.simpleresource.domain.dto.UserSearchDto;
import com.dcn.simpleresource.domain.entity.UserEntity;
import com.dcn.simpleresource.domain.mapper.UserMapper;
import com.dcn.simpleresource.repository.UserRepository;
import com.dcn.simpleresource.service.UserService;
import com.dcn.simpleresource.utils.PaginationUtils;
import com.dcn.simpleresource.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> list = repository.findAll();
        return mapper.toDto(list);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        Optional<UserEntity> user = repository.findOneByUsername(username);
        ValidationUtils.isNull(user, "User", "username", username);
        return mapper.toDto(user.get());
    }

    @Override
    public UserDto create(UserDto dto) {
        UserEntity userEntity = mapper.toEntity(dto);
        userEntity.setIsDeleted(0L);
        UserEntity entity = repository.save(userEntity);
        return mapper.toDto(entity);
    }

    @Override
    public Page<UserDto> search(UserSearchDto dto) {
        PaginationDto paginationDto = repository.filter(dto.getUsername(), dto.getFullName(),
                dto.getTelephone(), dto.getEmail(), dto.getPageSize(), dto.getPageNumber());
        List<UserEntity> listEntity = (List<UserEntity>) paginationDto.getList();
        List<UserDto> listDto = mapper.toDto(listEntity);
        Pageable pageable = PaginationUtils.buildPageable(paginationDto);
        return new PageImpl<>(listDto, pageable, paginationDto.getTotal());
    }

    @Override
    public void update(UserDto dto) {
        this.validateIdExists(dto.getId());
        UserEntity userToUpdate = mapper.toEntity(dto);
        userToUpdate.setIsDeleted(0L);
        repository.save(userToUpdate);
    }

    @Override
    public void delete(Long id) {
        this.validateIdExists(id);
        repository.deleteById(id);
    }

    void validateIdExists(Long id) {
        Optional<UserEntity> oldUser = repository.findOneById(id);
        ValidationUtils.isNull(oldUser, "User", "id", id);
    }
}
