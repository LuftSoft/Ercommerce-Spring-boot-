package com.luffschloss.shop.model;

import java.io.Serializable;
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
@Table(name = "insert_goods")
public class InsertGoods implements Serializable{
	@Id
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="product_item_id")
	private ProductItem product_item_id;
	@Id
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="shop_id")
	private Shop shop_id;
	@Column(columnDefinition = "int not null")
	private int quantity;
	@Column(columnDefinition = "datetime not null")
	private Calendar create_date;
}
