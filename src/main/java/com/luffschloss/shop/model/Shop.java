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
@Table(name="shop")
public class Shop implements Serializable{
	@Id
	@Column(columnDefinition="nvarchar(100)")
	private String id;
	@Column(columnDefinition="nvarchar(255) not null")
	private String name;
	@Column(columnDefinition="nvarchar(255) not null",unique = true)
	private String slug;
	@JsonIgnore
	@OneToOne(mappedBy = "shop_id")
	private User user_id;
	@JsonIgnore
	@OneToMany(mappedBy = "shop_id")
	private List<Product> products;
	@JsonIgnore
	@OneToMany(mappedBy = "shop_id")
	private List<InsertGoods> insertgoods;
}
