package com.luffschloss.shop.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
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
@Table(name="status")
public class Status implements Serializable{
	@Id
	@Column(columnDefinition="nvarchar(100)")
	private String id;
	@Column(columnDefinition="nvarchar(100)")
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "status_id")
	private List<OrderStatus> order_status;
}
