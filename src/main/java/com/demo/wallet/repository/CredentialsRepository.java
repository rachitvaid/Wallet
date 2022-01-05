package com.demo.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.wallet.model.Credentials;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials,String> {

}
