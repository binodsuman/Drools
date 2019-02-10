package com.binod.DroolsSession;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 * 
 * @author Binod Suman
 * Binod Suman Academy YouTube
 *
 */

public class DemoTest {

	public static void main(String[] args) {
	  try{	
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("first-ksession-rule");
		
		PaymentOffer paymentOffer = new PaymentOffer();
		paymentOffer.setChannel("paytm");
		paymentOffer.setFestival("xmas");
		paymentOffer.setFirstTimeCustomer(true);
		
		PaymentCounter counter = new PaymentCounter();
		counter.setChannel("Amazon");
		counter.setAmount(10);
		
		
		
		FactHandle factHandler;
		
		factHandler = kSession.insert(paymentOffer);
		kSession.insert(counter);
		
		
		counter = new PaymentCounter();
		counter.setChannel("XYZ");
		counter.setAmount(20);
		kSession.insert(counter);
		
		counter = new PaymentCounter();
		counter.setChannel("ABC");
		counter.setAmount(90);		
		kSession.insert(counter);
		
		kSession.fireAllRules();
		
	   System.out.println("The cashback for this payment channel VIA KIE "+paymentOffer.getChannel()+" is "+paymentOffer.getDiscount());
	    } catch(Exception e){
	    	e.printStackTrace();
	    }

	}

}
