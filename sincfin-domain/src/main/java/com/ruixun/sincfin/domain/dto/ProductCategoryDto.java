package com.ruixun.sincfin.domain.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruixun.sincfin.domain.enums.ProductCategoryEnum;

public class ProductCategoryDto implements Comparable<ProductCategoryDto>{
	
	private Integer categoryId;
	private String categoryCode;
	private String categoryName;
	private String categoryIcon;
	
	private Integer categorySort;
	
	
	private List<ProductViewDto> products;
	
	public ProductCategoryDto() {
		
	}
	
	public ProductCategoryDto(ProductCategoryEnum productCategoryEnum) {
		this.categoryId = productCategoryEnum.getId();
		this.categoryCode = productCategoryEnum.getCode();
		this.categoryName = productCategoryEnum.getName();
		this.categoryIcon = productCategoryEnum.getIcon();
		
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryIcon() {
		return categoryIcon;
	}

	public void setCategoryIcon(String categoryIcon) {
		this.categoryIcon = categoryIcon;
	}

	public List<ProductViewDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductViewDto> products) {
		this.products = products;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@JsonIgnore
	public Integer getCategorySort() {
		return categorySort;
	}

	public void setCategorySort(Integer categorySort) {
		this.categorySort = categorySort;
	}

	@Override
	public int compareTo(ProductCategoryDto dto) {
		return this.getCategorySort().compareTo(dto.getCategorySort());
	}
	
	
	

}
