package cn.tinder.fuego.util.engine.computer;

import java.util.Date;

import org.junit.Test;

import cn.tinder.fuego.util.date.DateService;

public class ComputeService {
	static public float cptValue(Date startDate,int limit,float value){
		float v;
		Date today= new Date();
		long l=today.getTime()-startDate.getTime();
		l=l/1000;
		l=l/3600;
		l=l/24;
		v=l/30;
		v=v/12;
		v=v/((long)limit);		
		v=value*v;

		/*
		 * Update by Nanbowen
		 * For Issue #43
		 */
		return value-v;	
	}

}
