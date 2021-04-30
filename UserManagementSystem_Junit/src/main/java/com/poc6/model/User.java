package com.poc6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component

public class User {
	
	@Id
	@Column(name = "id" )
	@ApiModelProperty(notes = "Id of user",name="id",value="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	
	@ApiModelProperty(notes = "First name of user",name="fname",required=true,value="fname")
	@Column(name = "First_Name", nullable = false)
	private String fname;
	
	@ApiModelProperty(notes = "Last name of user",name="lname",required=true,value="lname")
	@Column(name = "Last_Name" , nullable = false)
	private String lname;
	
	@ApiModelProperty(notes = "Contact of user",name="contact",required=true,value="contact")
	@Column(name = "Contact", nullable = false, unique=true )
	@Pattern(regexp = "^[789]\\d{9}$")
	private String contact;
	
	@ApiModelProperty(notes = "Email of user",name="email",required=true,value="email")
	@Column(name = "Email", nullable = false,unique = true)
	private String email;
	
	
	@ApiModelProperty(notes = "City of user",name="city",required=true,value="city")
	@Column(name = "City", nullable = false)
	private String city;
	
	@ApiModelProperty(notes = "State of user",name="state",required=true,value="state")
	@Column(name = "State", nullable = false)
	private String state;
	
	@ApiModelProperty(notes = "Country of user",name="country",required=true,value="country")
	@Column(name = "Country" ,nullable = false)
	private String country;
	
	
	@ApiModelProperty(notes = "Pincode of user",name="pincode",required=true,value="pincode")
	@Column(name = "Pincode" ,nullable = false)
	private String pincode;
	
	
}
