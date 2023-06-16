package com.plantparadisemarket.model;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private int customerId;
	
	@NotBlank
	@NotNull
	@NotEmpty
	@Size(min = 2)
	private String customerName;
	
	@Email(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",message = "Invalid Email Id")
	@Column(unique = true)
	private String customerEmail;
	
	
	@NotBlank
	@NotNull
	@NotEmpty
	@Column(unique = true)
	@Pattern(regexp = "^[6-9]\\d{9}$",message = "Invalid Phone Number")
	private String phoneNo;
	
	
	@NotBlank
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])[a-zA-Z\\d@#$%^&+=]{8,}$",message = "Invalid Password, your password should contain atleast one number, one special character, one capital letter and rest can be small letters")
	private String password;
	
	
	@Embedded
	private Address address;
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "customer")
	@JsonIgnore
	private List<Orders> orderList= new ArrayList<>();
	
	
}
