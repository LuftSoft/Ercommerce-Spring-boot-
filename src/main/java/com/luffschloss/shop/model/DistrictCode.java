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
@Table(name="DistrictCode")
public class DistrictCode implements Serializable{
	@Id
	@Column(columnDefinition="nvarchar(100)")
	private String id;
	@Column(columnDefinition="nvarchar(255) not null")
	private String name;
	@Column(columnDefinition="nvarchar(255) not null")
	private String type;
	@JsonIgnore
	@OneToMany(mappedBy = "district_id")
	private List<HomeletCode> homelets;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "provine_id",nullable = false)
	protected ProvineCode provine_id;
	
	
}
