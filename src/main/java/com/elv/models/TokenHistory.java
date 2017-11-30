package com.elv.models;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="tokenhistory")
public class TokenHistory {
	@Id
	@Column(length=500)
	private String tokenValue;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar expireOn;
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	
	public Calendar getExpireOn() {
		return expireOn;
	}
	public void setExpireOn(Calendar expireOn) {
		this.expireOn = expireOn;
	}
	@Override
	public String toString() {
		return "tokenHistory [tokenValue=" + tokenValue + ", expireOn="
				+ expireOn + "]";
	}
	
}
