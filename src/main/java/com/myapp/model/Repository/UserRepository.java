package com.myapp.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.myapp.model.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Metode lain jika diperlukan
}
