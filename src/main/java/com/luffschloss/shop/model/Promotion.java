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
@Table(name = "promotion")
public class Promotion implements Serializable{
	@Id
	@Column(columnDefinition = "nvarchar(100)")
	private String id;
	@Column(length=100, columnDefinition = "nvarchar(100) not null")
	private String name;
	@Column(columnDefinition = "text not null")
	private String description;
	@Column(nullable=false, columnDefinition="Datetime")
	private Calendar start_date;
	@Column(nullable=false, columnDefinition="Datetime")
	private Calendar end_date;
	@OneToMany(mappedBy = "promotion_id",cascade = CascadeType.ALL)
	private List<PromotionDetail> promotion_detail;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Admin admin_id;
}
