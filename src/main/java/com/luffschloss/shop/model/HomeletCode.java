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
@Table(name="HomeletCode")
public class HomeletCode implements Serializable{
	@Id
	@Column(columnDefinition="nvarchar(5)")
	private String id;
	@Column(columnDefinition="nvarchar(255) not null")
	private String name;
	@Column(columnDefinition="nvarchar(255) not null")
	private String type;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	protected DistrictCode district_id;
	@JsonIgnore
	@OneToMany(mappedBy = "homlet_code_id")
	protected List<Address> user_address;
}
