package com.luffschloss.shop.model;

import java.io.Serializable;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promotion_detail")
public class PromotionDetail implements Serializable{
	@Id
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="promotion_id", nullable = false)
	private Promotion promotion_id;
	@Id
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="product_id", nullable = false)
	private Product product_id;
	@Column(nullable = false)
	private Double rate=0.0;
}
