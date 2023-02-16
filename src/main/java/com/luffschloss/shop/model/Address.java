package com.luffschloss.shop.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Address")
@NamedQuery(name = "Address.checkIdExitst",
query = "select id from Address where id=1")
public class Address implements Serializable{
	@Id
	@Column(columnDefinition="nvarchar(100)")
	private String id;
	@Column(columnDefinition="nvarchar(300) not null")
	private String address;
	@Column()
	private Boolean is_main_address=false;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="homlet_code_id", nullable = false)
	private HomeletCode homlet_code_id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user_id;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="admin_id")
	private Admin admin_id;
}
