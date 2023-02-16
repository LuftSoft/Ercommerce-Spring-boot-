package com.luffschloss.shop.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Category")
public class Category implements Serializable{
	@Id
	@Column(columnDefinition="nvarchar(100)")
	private String id;
	@Column(columnDefinition="nvarchar(255) not null",unique = true)
	private String name;
	@JsonIgnore
	@ManyToOne()
	private Category parent_category_id;
	@JsonIgnore
	@OneToMany(mappedBy = "parent_category_id")
	private List<Category> child_categories;
	@JsonIgnore
	@OneToMany(mappedBy = "category_id")
	private List<Product> products;
	
	
}
