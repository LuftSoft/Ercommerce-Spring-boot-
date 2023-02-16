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
@Table(name="admin")
public class Admin implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition="nvarchar(255) not null")
	private String name;
	@Column(columnDefinition="nvarchar(15) not null",unique = true)
	private String phone;
	@Column(columnDefinition="nvarchar(100)",unique = true)
	private String email;
	@JsonIgnore
	@OneToMany(mappedBy = "admin_id")
	private List<Promotion> promotions;
	@JsonIgnore
	@OneToOne(mappedBy = "admin_id")
	protected Address address_code;
}
