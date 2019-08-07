package com.smart.helmet.notification.impl;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smart.helmet.notification.NotificationService;



@Service
public class SMSNotificationService implements NotificationService{
	private static final Logger logger=LoggerFactory.getLogger(SMSNotificationService.class);

	
	@Override
	public boolean sendNotification(String mobile, String subject, String message) {
		
		boolean flag=false;
		String loghead="sendNotification() To :"+mobile+" :: ";
		try {
		 RestTemplate restTemplate = new RestTemplate();
		        String resp=restTemplate.getForObject("http://api.msg91.com/api/v2/sendsms?country=91&sender=SHELMT&route=4&mobiles="+mobile+"&authkey=268449AOjrOOqpuNTz5c91c6af&message="+URLEncoder.encode(message),String.class);
		   logger.info(loghead+"SMS Sending response :: "+resp);
		flag=true;
		}catch(Exception e) {
			logger.error(loghead+"exception while sending SMS "+e);
		}
		return flag;
	}

}
