package com.backend.service;

import com.backend.entity.Priority;

public interface IPrioritiesService {
	
	Priority findById(long Id);
	Priority save(Priority cliente);
	void delete(Long id);
}
