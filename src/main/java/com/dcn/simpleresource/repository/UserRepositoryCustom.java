package com.dcn.simpleresource.repository;

import com.dcn.simpleresource.domain.dto.PaginationDto;

public interface UserRepositoryCustom {

    PaginationDto filter(String username, String fullName, String phoneNumber, String email, int pageSize, int pageNumber);
}
