package com.luffschloss.shop.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Role {
	@Id
	@Column(name="RoleId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="Rolename")
	@Enumerated(EnumType.STRING)
	private ERole rolename; 
}
