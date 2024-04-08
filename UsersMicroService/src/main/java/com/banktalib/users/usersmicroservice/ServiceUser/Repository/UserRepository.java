package com.banktalib.users.usersmicroservice.ServiceUser.Repository;

import com.banktalib.users.usersmicroservice.ServiceUser.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserByUsername(String username);
}
