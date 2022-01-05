package com.demo.wallet.model;

import javax.persistence.*;

import lombok.*;

@Setter
@Getter
@Data
@Entity(name="credentials")
@Table(name="credentials")
public class Credentials {
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="userid")
	private String userid;

}
