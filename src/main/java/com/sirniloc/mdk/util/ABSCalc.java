package com.sirniloc.mdk.util;

public class ABSCalc {

    public static final int MAXABILITYSCORE = 30;
    public static final int MAXABILITYMOD = 10;
    public static final int MINABILITYMOD = -5;

	public static int calcMod(int aScore) {

    	if(aScore == 1) return -5;
    	if(aScore <= 3) return -4;
    	if(aScore <= 5) return -3;
    	if(aScore <= 7) return -2;
    	if(aScore <=9) return -1;
    	if(aScore <=11) return 0;
    	if(aScore <=13) return 1;
    	if(aScore <=15) return 2;
    	if(aScore <=17) return 3;
    	if(aScore <=19) return 4;
    	if(aScore <=21) return 5;
    	if(aScore <=23) return 6;
    	if(aScore <=25) return 7;
    	if(aScore <=27) return 8;
    	if(aScore <=29) return 9;
    	if(aScore ==30) return 10;
    	return -5;
    }
}
