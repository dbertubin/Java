package com.petrockz.astrology.helpers;

public enum Signs {
	ARIES(1),
	TAURUS(2),
	GEMINI(3),
	CANCER(4),
	LEO(5),
	VIRGO(6),
	LIBRA(7),
	SCORPIO(8),
	SAGITTARRIUS(9),
	CAPRICORN(10),
	AQUARIUS(11),
	PISCES(12); 
	

	private final int value;
	
	Signs(int value){
		this.value= value;
	}

	public int getValue() {
		return value;
	}
	
	
}
