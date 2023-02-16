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
@Table(name="order_status")
public class OrderStatus implements Serializable{
	@Id
	@JsonIgnore
	@ManyToOne
	@JoinColumn()
	private Order order_id;
	@Id
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status_id;
	@Column(columnDefinition="datetime not null")
	private Calendar create_datetime;
	@Column(columnDefinition = "text not null")
	private String history="";
}
