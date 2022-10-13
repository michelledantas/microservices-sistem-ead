package com.ead.authuser.repositories;

import com.ead.authuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserRepository extends JpaRepository <UserModel, UUID>, JpaSpecificationExecutor<UserModel> { //aqui é preciso fazer extender também o JpaSpecification

    boolean existsByUsername (String username);
    boolean existsByEmail (String email);

}
