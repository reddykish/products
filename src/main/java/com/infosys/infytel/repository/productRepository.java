package com.infosys.infytel.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infosys.infytel.dto.productDTO;
import com.infosys.infytel.entity.product;

public interface productRepository extends CrudRepository<product, String> {
			
	Optional<product> findByProdId(String ProductId);
	Optional<List<product>> findAllByProdName(String productname);
	Optional <List<product>> findAllByCategory(String category);
	//Optional<product> findTopByprodIdDesc();
	int deleteAllBySellerId(String sellerId);
	Optional <List<product>> findAllByCategoryAndSubCateg(String category,String Subcategory);
	
	Optional<product> findBySellerIdAndProdId(String sellerId,String prodId);
	product findTopByOrderByProdIdDesc();
	
	
}
