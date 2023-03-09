package com.mandate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mandate.model.EmandateData;

@Repository("")
public interface IEmandateRepository extends JpaRepository<EmandateData, Integer> {

}
