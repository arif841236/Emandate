package com.mandate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mandate.model.AccountVerificationData;

@Repository("postgresql")
public interface IAccountVerificationRepository extends JpaRepository<AccountVerificationData, Integer> {

}
