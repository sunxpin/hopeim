package com.tianyoukeji.parent.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tianyoukeji.parent.entity.template.MenuTemplate;

public interface LogRepository extends JpaRepository<Log, Long> {
	
	List<Log> findByEntityAndEntityId(String entity,Long entityId);
}
