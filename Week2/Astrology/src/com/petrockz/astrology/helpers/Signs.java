/*
 * 
 */
package com.petrockz.astrology.helpers;

public enum Signs {
	// Sign names 
	ARIES(1, "Aries"),
	TAURUS(2, "Taurus"),
	GEMINI(3, "Gemini"),
	CANCER(4, "Cancer"),
	LEO(5, "Leo"),
	VIRGO(6, "Virgo"),
	LIBRA(7, "Libra"),
	SCORPIO(8, "Scorpio"),
	SAGITTARRIUS(9,"Sagittarius"),
	CAPRICORN(10,"Capricorn"),
	AQUARIUS(11,"Aquarius"),
	PISCES(12,"Pisces"); 
	

	private int signCount;
	private String signName;
	
	
	private Signs(int signCount, String signName) {
		this.signCount = signCount;
		this.signName = signName;
		
	}
	
	public int getSignCount(){
		return signCount;
	}

	public String getSignName(){
		return signName;
	}
	
	
	
	
}
