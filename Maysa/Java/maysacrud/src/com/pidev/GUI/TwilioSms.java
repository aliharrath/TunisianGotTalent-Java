/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.GUI;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



/**
 *
 * @author Karim
 */
public class TwilioSms {

    public static final String ACCOUNT_SID = "AC6f039ad2f02f5b3e4b6e2b3b71a8e3bd";
    public static final String AUTH_TOKEN =  "62d4b9840d2b17b28d3f8e15badd0c7d";

    public void sendSms(String body) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(new PhoneNumber("+21652376526"),
        new PhoneNumber("+12057723553"), 
        body).create();
    }
}
