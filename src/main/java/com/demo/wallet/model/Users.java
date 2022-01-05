package com.demo.wallet.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Data
@Entity(name="Users")
@Table(name="Users")
public class Users {
	@Id
	@Column(name="userid")
	private String userid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="phnumber")
	private String phnumber;
	
	@Column(name="balance")
	private long balance;

}
