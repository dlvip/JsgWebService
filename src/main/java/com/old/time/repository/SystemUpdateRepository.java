package com.old.time.repository;

import com.old.time.domain.SystemBean;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface SystemUpdateRepository extends JpaRepository<SystemBean, Integer> {

}
