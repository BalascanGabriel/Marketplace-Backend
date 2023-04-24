package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
//	@Column(name="id_user")
//	private Integer idUser;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	
//	@Column(name="id_product")
//	private Integer idProduct;
	
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product product;
	
	@Column(name="quantity")
	private Integer quantity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public Integer getIdUser() {
//		return idUser;
//	}
//
//	public void setIdUser(Integer idUser) {
//		this.idUser = idUser;
//	}

//	public Integer getIdProduct() {
//		return idProduct;
//	}
//
//	public void setIdProduct(Integer idProduct) {
//		this.idProduct = idProduct;
//	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
