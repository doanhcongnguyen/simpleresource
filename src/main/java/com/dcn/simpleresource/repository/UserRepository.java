package com.dcn.simpleresource.repository;

import com.dcn.simpleresource.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {

    @Query("FROM UserEntity WHERE isDeleted = 0 ")
    List<UserEntity> findAll();

    Optional<UserEntity> findOneById(Long id);

    Optional<UserEntity> findOneByUsername(String username);

    @Override
    @Modifying
    @Query("UPDATE UserEntity SET isDeleted = 1 WHERE id = ?1")
    void deleteById(Long id);
}
