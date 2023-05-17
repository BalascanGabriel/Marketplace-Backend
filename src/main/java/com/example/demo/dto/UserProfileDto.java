package com.example.demo.dto;

import java.util.Date;
import java.util.List;

public class UserProfileDto {

	private final String address;
	private final Date dateOfBirth;
	private final List<String> deliveryAddresses;

	public UserProfileDto(String address, Date dateOfBirth, List<String> deliveryAddresses) {
		this.address = address;
//		this.dateOfBirth = dateOfBirth;
		
		this.deliveryAddresses = deliveryAddresses.size() <= 2 ? deliveryAddresses : null;
		this.dateOfBirth = dateOfBirth.before(new Date()) ? dateOfBirth : null;
		
		
	}

	public String getAddress() {
		return address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public List<String> getDeliveryAddresses() {
		return deliveryAddresses;
	}

	@Override
	public String toString() {
		return "UserProfileDto [address=" + address + ", dateOfBirth=" + dateOfBirth + ", deliveryAddresses="
				+ deliveryAddresses + "]";
	}
	
	
	
	
}
