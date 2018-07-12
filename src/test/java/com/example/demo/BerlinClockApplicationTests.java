package com.example.demo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hive.task.berlinclock.BerlinClock;
import com.hive.task.berlinclock.BerlinClockApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BerlinClockApplication.class)
public class BerlinClockApplicationTests {
	
	private static BerlinClock berlinClock;
	
	@Before
	public void setup() {
		berlinClock = new BerlinClock();
	}
	
	@After
	public void destroy() {
		berlinClock = null;
	}
	
	@Test
	public void berlinClockTest() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.UK);
		LocalTime time = LocalTime.of(21, 30, 59);
        String f = formatter.format(time);
        
        System.out.println("Current Local Time is : " + f);

        berlinClock = new BerlinClock();
        berlinClock.setHours(time.getHour());
        berlinClock.setMinutes(time.getMinute());
        
        Assert.assertEquals("Expected 4 Rs","R,R,R,R",berlinClock.getFiveHoursRow().toString());
        Assert.assertEquals("Expected 1 R,0,0,0","R,0,0,0",berlinClock.getOneHourRow().toString());
        Assert.assertEquals("Expected Y,Y,R,Y,Y,R,0,0,0,0,0","Y,Y,R,Y,Y,R,0,0,0,0,0",berlinClock.getFiveMinutesRow().toString());
        Assert.assertEquals("Expected 1 R","R,0,0,0",berlinClock.getOneHourRow().toString());
	}

	
}
