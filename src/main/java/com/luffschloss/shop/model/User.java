package com.luffschloss.shop.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User implements Serializable{
	@Id
	@Column(columnDefinition="int")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition="nvarchar(255) not null")
	private String name;
	@Column(columnDefinition="nvarchar(15) not null",unique = true)
	private String phone;
	@Column(columnDefinition="nvarchar(100)",unique = true)
	private String email;
	@JsonIgnore
	@Column(columnDefinition="nvarchar(MAX) not null")
	private String password;
	@JsonIgnore
	@OneToMany(mappedBy = "user_id")
	private List<Address> address;
	@JsonIgnore
	@OneToMany(mappedBy = "user_id")
	private List<Payment> payments;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "shop_id")
	protected Shop shop_id;
	@ManyToMany(fetch = FetchType.LAZY)//khi lay' user thi lay het luon cac  quyen cua user day'
	@JoinTable(name="User_Role",joinColumns = @JoinColumn(name="Id"),
	inverseJoinColumns = @JoinColumn(name="RoleId"))
	private Set<Role> setRole = new HashSet<>();
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="Cart",joinColumns =@JoinColumn(name="UserId"),
			inverseJoinColumns = @JoinColumn(name="ProductItemId"))
	private Set<ProductItem> setProductItem = new HashSet<>();
	
	
	public boolean addToCart(ProductItem p) {
		try {
			this.setProductItem.add(p);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
