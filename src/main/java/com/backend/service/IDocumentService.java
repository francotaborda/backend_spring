
package com.backend.service;

import java.util.List;

import com.backend.entity.IDType;

public interface IDocumentService {
List<IDType> findAll();
		
	//public Page<Company> findAll(Pageable pageable);

	IDType findById(Long id);

	IDType save(IDType IDType);

	void delete(Long id);
		
	IDType update(Long id, IDType IDTypeNew);
		
	

}
