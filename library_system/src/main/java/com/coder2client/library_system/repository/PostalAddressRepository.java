package com.coder2client.library_system.repository;

import com.coder2client.library_system.entity.PostalAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalAddressRepository extends JpaRepository<PostalAddress, Long> {
}
