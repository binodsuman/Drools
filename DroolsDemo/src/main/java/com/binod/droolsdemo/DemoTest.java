package com.binod.droolsdemo;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;

/**
 * 
 * @author Binod Suman
 * Binod Suman Academy YouTube
 *
 */

public class DemoTest {

	public static void main(String[] args) throws DroolsParserException, IOException {
		DemoTest client = new DemoTest();
        client.execteRule();
	}

	public void execteRule() throws DroolsParserException, IOException{
		   PackageBuilder builder = new PackageBuilder();
		   String ruleFile = "/offers.drl";
		   InputStream resourceAsStream = getClass().getResourceAsStream(ruleFile);
		   
		   Reader ruleReader = new InputStreamReader(resourceAsStream);
		   builder.addPackageFromDrl(ruleReader);
		   org.drools.core.rule.Package rulePackage = builder.getPackage();
		   RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		   ruleBase.addPackage(rulePackage);
		   
		   WorkingMemory workingMemory = ruleBase.newStatefulSession();
		   
		   PaymentOffer paymentOffer = new PaymentOffer();
		   paymentOffer.setChannel("paytm");
		   workingMemory.insert(paymentOffer);
		   workingMemory.fireAllRules();
		   
		   System.out.println("The cashback for this payment channel "+paymentOffer.getChannel()+" is "+paymentOffer.getDiscount());
	       
	   } 
}
