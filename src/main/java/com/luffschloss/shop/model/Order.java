package com.luffschloss.shop.model;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name="orders")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition="datetime not null")
	private Calendar create_datetime;
	@Column(columnDefinition="int not null")
	@Min(1)
	private int total;
	@JsonIgnore
	@OneToMany(mappedBy = "order_id")
	private List<OrderItem> order_item;
	@JsonIgnore
	@OneToMany(mappedBy = "order_id")
	private List<OrderStatus> order_status;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Payment payment_id;
}
