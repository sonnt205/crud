package com.ezcload.user.demo.demo.repository;

import com.ezcload.user.demo.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
