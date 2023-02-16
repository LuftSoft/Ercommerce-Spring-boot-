package com.luffschloss.shop.model;

import java.io.Serializable;
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
@Table(name = "product_item")
public class ProductItem implements Serializable{
	@Id
	@Column(columnDefinition = "nvarchar(100)")
	private String id;
	@Column(unique=true,length=100, columnDefinition = "nvarchar(100) not null")
	private String name;
	@Column(nullable=false)@Min(1)
	private Double price;
	@Column(columnDefinition = "nvarchar(30) not null")@Min(0)
	private String size;
	@Column(nullable=false)@Min(0)
	private int quantity;
	@Column(columnDefinition = "nvarchar(255) not null",unique = true)
	private String image;
	@JsonIgnore
	@OneToMany(mappedBy = "product_item_id")
	private List<OrderItem> order_item;
	@JsonIgnore
	@OneToMany(mappedBy = "product_item_id")
	private List<InsertGoods> insert_goods;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product_id;
}
