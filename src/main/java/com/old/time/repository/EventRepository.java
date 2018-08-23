package com.old.time.repository;

import com.old.time.domain.EventEntity;
import com.old.time.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
}
