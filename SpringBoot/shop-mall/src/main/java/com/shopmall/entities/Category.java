package com.shopmall.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name=Category.CATEGORY)
public class Category {
	
	/**
	 *
	 */
	static final String CATEGORY = "category";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty(message="Tên danh mục không được trống")
	private String nameCategory;

	@JsonIgnore
	@OneToMany(mappedBy = CATEGORY)
	private List<Product> listProduct;
	
//	public Category(long id, String NameCategory) {
//		this.id = id;
//		this.NameCategory = NameCategory;
//	}
	

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
