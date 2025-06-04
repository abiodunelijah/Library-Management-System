package com.coder2client.library_system.repository;

import com.coder2client.library_system.entity.CheckoutRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRegisterRepository extends JpaRepository<CheckoutRegister, Long> {
}
