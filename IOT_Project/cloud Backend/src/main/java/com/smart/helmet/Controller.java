package com.smart.helmet;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smart.helmet.notification.impl.SMSNotificationService;





@RestController
@RequestMapping("/smart-helmet")
public class Controller {
	private static final Logger log = LoggerFactory.getLogger(Controller.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
	private SMSNotificationService smsNotificationService;
	@RequestMapping("/")
	public String testing() {
		return "working";
	}
	@RequestMapping("/send")
	@ResponseBody
	public String addEmployee(@RequestParam (name="status")int status,
			@RequestParam (name="mobile")String mobile,
			@RequestParam (name="lat")String lat,
			@RequestParam (name="long")String lng) {
		log.debug("REQUEST :: Latitude :: "+lat+" | Longitude :: "+lng+" | Status :: "+status+" |mobile :: "+mobile);
		System.out.println("REQUEST :: Latitude :: "+lat+" | Longitude :: "+lng+" | Status :: "+status+" |mobile :: "+mobile);
		
		String sub="";
		String message="";
		boolean sendFlag=false;
		String mes="ACCIDENT !!   Location:  https://www.google.com/maps/search/?api=1&query="+lat+","+lng+"";
		if(status==1){message="RASH DRIVING !!";sendFlag=true;}
		else if(status==2){message=mes;sendFlag=true;}
		if(sendFlag){smsNotificationService.sendNotification(mobile, sub, message);log.debug("MESSAGE SENT !!");}
		else log.debug("IGNOREING REQUEST !!"); 
		
		//processing 
		return "OK";
	}
}
