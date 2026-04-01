package com.arteSul.pedidosystem.repository;

import com.arteSul.pedidosystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
