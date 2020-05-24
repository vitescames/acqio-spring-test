package com.acqio.teste.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB_TRANSACTION")
@SequenceGenerator(allocationSize=1, name="transaction", sequenceName="SQ_TB_TRANSACTION")
public class TransactionVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_transaction")
	@GeneratedValue(generator="transaction", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	//Não inseri coluna LocalDate pois o LocalDateTime abaixo já armazena a data e horário
	//Usei LocalDateTime pois é o que está pedindo para ser usado no teste
	
	@Column(name="transaction_time", nullable=false)
	private LocalDateTime time;
	
	@Column(name="transaction_value", nullable=false)
	private BigDecimal value;
	
	@Enumerated(EnumType.STRING)
	@Column(name="card_application", nullable=false, length=100)
	private CardApplication cardApplication;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_status", nullable=false, length=100)
	private PaymentStatus status;
	
	public TransactionVO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public CardApplication getCardApplication() {
		return cardApplication;
	}

	public void setCardApplication(CardApplication cardApplication) {
		this.cardApplication = cardApplication;
	}
	
}
