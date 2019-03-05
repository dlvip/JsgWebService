package com.old.time.repository;

import com.old.time.domain.SignNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignNameRepository extends JpaRepository<SignNameEntity, Integer> {


    SignNameEntity findSignNameEntityByUserIdAndId(String userId, String id);



}
