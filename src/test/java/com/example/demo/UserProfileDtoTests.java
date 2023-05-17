package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.UserProfileDto;

@SpringBootTest
public class UserProfileDtoTests {

	private static Date createDate(int year, int month, int day) {
		Date date = null;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2025);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 17);
		date = cal.getTime();
		
//		Date date = Calendar.getInstance().year(2025).month(1).day(17).build();
		return date;
	}
	
	private UserProfileDto createOkObject() {
		Date datePast = createDate(1990, 1, 10);
		List<String> addresses = new ArrayList<>();
		addresses.add("USA, NY, Bronks");
		addresses.add("USA, NY, Harlem");
		UserProfileDto dto = new UserProfileDto("USA, NY, Queens", datePast, addresses);
		return dto;
	}
	
	// metoda care creeaza un obiect "faulty"
	private UserProfileDto createObjectWith3Addresses() {
		Date d = new Date();
		List<String> addresses = new ArrayList<>();
		addresses.add("USA, NY, Bronks");
		addresses.add("USA, NY, Harlem");
		addresses.add("USA, NY, Harlem 2");
		UserProfileDto dto = new UserProfileDto("USA, NY, Queens", d, addresses);
		return dto;
	}
	
	private UserProfileDto createObjectWithDOBInTheFuture() {
		List<String> addresses = new ArrayList<>();
		addresses.add("USA, NY, Bronks");
		addresses.add("USA, NY, Harlem");
		Date sometimeInTheFuture = createDate(2025, 1,20);
		
		UserProfileDto dto = new UserProfileDto("USA, NY, Queens", sometimeInTheFuture, addresses);
		return dto;
	}
	
	// test care verifica conditia ca nu poti avea mai mult de 2 delivery addresses pe obiectul UserProfileDto
	@Test
	public void testUserProfileDeliveryAddress() {
		
		UserProfileDto dto = createObjectWith3Addresses();
		
		// assertEquals(2, dto.getDeliveryAddresses().size());
		assertNull(dto.getDeliveryAddresses());
	}
	
	@Test
	public void testUserProfileDateOfBirth() {
		UserProfileDto dto = createObjectWithDOBInTheFuture();
		
//		Date now = new Date();
		// assertTrue(dto.getDateOfBirth().before(now));
		assertNull(dto.getDateOfBirth());
		
	}
	
	@Test
	public void checkObjectImmutability() {
		UserProfileDto dto = createOkObject();
		dto.getDeliveryAddresses().add("ADRESA NOUA");
		assertEquals(2, dto.getDeliveryAddresses().size());
	}
	
}
