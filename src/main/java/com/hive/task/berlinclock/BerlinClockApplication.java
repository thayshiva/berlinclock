package com.hive.task.berlinclock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BerlinClockApplication {

	private static final Logger logger = LogManager.getLogger(BerlinClockApplication.class);
	
	private static BerlinClock berlinClock;
	
	public static void main(String[] args) {
		SpringApplication.run(BerlinClockApplication.class, args);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.UK);
        LocalTime time = LocalTime.now();
        String f = formatter.format(time);
        logger.info("  ");
        logger.info("Current Local Time is : " + f);

        berlinClock = new BerlinClock();
        berlinClock.setHours(time.getHour());
        berlinClock.setMinutes(time.getMinute());
        
        while(true) {
        	try {
				logger.info(berlinClock.getSecondsLamp().toString());
	        	logger.info(berlinClock.getFiveHoursRow().toString());
	            logger.info(berlinClock.getOneHourRow().toString());
	            logger.info(berlinClock.getFiveMinutesRow().toString());
	            logger.info(berlinClock.getOneMinuteRow().toString());
	            
	            logger.info("Sleeping for a minute");
	            Thread.sleep(60000);
	            logger.info("Back from sleep");
	            time = LocalTime.now();
	            f = formatter.format(time);
	            logger.info("Current Local Time is : " + f);

	            berlinClock.setHours(time.getHour());
	            berlinClock.setMinutes(time.getMinute());

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
	}
}
