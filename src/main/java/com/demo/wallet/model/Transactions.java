package com.demo.wallet.model;

import lombok.*;
import javax.persistence.*;
@Getter
@Setter
@Data

@Entity(name="Transactions")
@Table(name="Transactions")
public class Transactions {
	
	@Id
	@Column(name="transactionId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long transactionId;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="type")
	private String type;
	
	@Column(name="amount")
	private long amount;
	
	@Column(name="balance")
	private long balance;

}
