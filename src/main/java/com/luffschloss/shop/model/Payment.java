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
@Table(name="payment")
public class Payment implements Serializable{
	@Id
	@Column(columnDefinition="nvarchar(100)")
	private String id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user_id;
	@Column(columnDefinition="nvarchar(20) not null")
	private String card_number;
	@Column(columnDefinition="nvarchar(15) not null")
	private String momo_number;
	@JsonIgnore
	@OneToMany(mappedBy = "payment_id")
	private List<Order> orders;
}
