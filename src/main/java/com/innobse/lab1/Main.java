package com.innobse.lab1;

import com.innobse.lab1.DBHelper.DBHelper;
import com.innobse.lab1.DBHelper.models.STC_Call;
import com.innobse.lab1.DBHelper.models.STC_CallReason;
import com.innobse.lab1.DBHelper.models.STC_Email;
import com.innobse.lab1.DBHelper.models.STC_EmailReason;
import com.innobse.lab1.JAXB.Parser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bse71 on 21.02.2017.
 */
public class Main {
    public static void main(String[] args) {
//        backupAll();
//        recoveryAll();
        recoveryEmail();
        recoveryCall();
    }

    private static void backupAll(){
        ExecutorService es = Executors.newFixedThreadPool(4);
        es.execute(new Runnable() {
            @Override
            public void run() {
                backupCall();
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                backupCallReasons();
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                backupEmail();
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                backupEmailReasons();
            }
        });
        es.shutdown();

    }

    private static void backupCall(){
        STC_Call call = new STC_Call();
        Parser.saveObject("call.xml", call);
    }

    private static void backupCallReasons(){
        STC_CallReason callR = new STC_CallReason();
        Parser.saveObject("call-reasons.xml", callR);
    }

    private static void backupEmail(){
        STC_Email email = new STC_Email();
        Parser.saveObject("email.xml", email);
    }

    private static void backupEmailReasons(){
        STC_EmailReason emailR = new STC_EmailReason();
        Parser.saveObject("email-reasons.xml", emailR);
    }

    private static void recoveryAll(){
        ExecutorService es = Executors.newFixedThreadPool(4);
        es.execute(new Runnable() {
            @Override
            public void run() {
                recoveryCall();
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                recoveryCallReasons();
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                recoveryEmail();
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                recoveryEmailReasons();
            }
        });
        es.shutdown();

    }

    private static void recoveryCall(){
        STC_Call calls = Parser.getObject("call.xml", new STC_Call());
        calls.toDB();
    }

    private static void recoveryCallReasons(){
        STC_CallReason callRs = Parser.getObject("call-reasons.xml", new STC_CallReason());
        callRs.toDB();
    }

    private static void recoveryEmail(){
        STC_Email emails = Parser.getObject("email.xml", new STC_Email());
        emails.toDB();
    }

    private static void recoveryEmailReasons(){
        STC_EmailReason emailRs = Parser.getObject("email-reasons.xml", new STC_EmailReason());
        emailRs.toDB();
    }
}
