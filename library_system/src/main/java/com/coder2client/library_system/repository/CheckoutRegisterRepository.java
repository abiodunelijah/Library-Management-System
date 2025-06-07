package com.coder2client.library_system.repository;

import com.coder2client.library_system.entity.CheckoutRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutRegisterRepository extends JpaRepository<CheckoutRegister, Long> {

    List<CheckoutRegister> findByMemberId(Long memberId);
    List<CheckoutRegister> findByBookId(Long bookId);
}
