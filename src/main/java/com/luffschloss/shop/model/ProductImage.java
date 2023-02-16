package com.luffschloss.shop.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_image")
public class ProductImage implements Serializable{
	@Id
	@Column(columnDefinition = "nvarchar(100)",unique = true)
	private String id;
	@Id
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product_id;
	@Column(columnDefinition = "nvarchar(100) not null",unique = true)
	private String Slug;
}
