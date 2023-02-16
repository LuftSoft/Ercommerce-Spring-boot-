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
@Table(name="order_item")
public class OrderItem implements Serializable{
	@Id
	@JsonIgnore
	@ManyToOne
	@JoinColumn()
	private ProductItem product_item_id;
	@Id
	@JsonIgnore
	@ManyToOne
	@JoinColumn()
	private Order order_id;
	@Column(nullable = false)
	private int quantity;
}
