package com.luffschloss.shop.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique=true, columnDefinition = "nvarchar(100) not null")
	private String name;
	@Column(nullable=false)@Min(1)
	private Double price;
	@OneToMany(mappedBy = "product_id",cascade = CascadeType.ALL)
	private List<ProductImage> product_image;
	@Column(nullable=false, columnDefinition="Datetime")
	private Calendar createdate;
	@Column(columnDefinition = "nvarchar(100) not null",unique = true)
	private String Slug;
	@Column(columnDefinition = "text")
	private String Description;
	@Column(nullable=false)
	private int sold=0;
	@Column(columnDefinition = "text not null")
	private String Attr;
	@Column(nullable=false,columnDefinition = "bit default(1)")
	private Boolean stillsale=true;
	@JsonIgnore
	@OneToMany(mappedBy = "product_id",cascade = CascadeType.ALL)
	private List<PromotionDetail> promotion_detail;
	@JsonIgnore
	@OneToMany(mappedBy = "product_id",cascade = CascadeType.ALL)
	private List<ProductItem> product_item;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "category_id",nullable = false)
	private Category category_id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="shop_id",nullable = false)
	private Shop shop_id;
}
