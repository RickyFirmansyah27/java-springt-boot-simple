package com.myapp.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myapp.model.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
