package com.shopmall.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.shopmall.dto.ProductDto;
import com.shopmall.dto.SearchProductObject;
import com.shopmall.entities.Product;

public interface ProductService {

	Product save(ProductDto sp);

	Product update(ProductDto sp);

	void deleteById(long id);

	Page<Product> getAllProductByFilter(SearchProductObject object, int page, int limit);

	Product getProductById(long id);
	
	List<Product> getLatestProduct();
	
	Iterable<Product> getProductByNameProductWithoutPaginate(SearchProductObject object);
	
	Page<Product> getProductByNameProduct(SearchProductObject object,int page, int resultPerPage);
	
	List<Product> getAllProductByList(Set<Long> idList);
	
	Page<Product> getProductByNameProductForAdmin(String NameProduct, int page, int size);
	
	Iterable<Product> getProductByNameCategory(String brand);
	
	public Page<Product> getProductByBrand(SearchProductObject object, int page, int resultPerPage);
}
