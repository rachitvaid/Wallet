package com.demo.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.wallet.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,String> {

}
