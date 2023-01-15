package com.fse2.lms.repository;

import com.fse2.lms.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    public boolean existsUserByUserEmailId(String userEmailId);


}
