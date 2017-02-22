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
    private static STC_Call currentCall = new STC_Call();
    private static STC_Email currentEmail = new STC_Email();
    private static STC_CallReason currentCallReason = new STC_CallReason();
    private static STC_EmailReason currentEmailReason = new STC_EmailReason();
    public static void main(String[] args) {
//        backupAll();
        recoveryAll();



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
        currentCall.fromDB();
        Parser.saveObject("call.xml", currentCall);
    }

    private static void backupCallReasons(){
        currentCallReason.fromDB();
        Parser.saveObject("call-reasons.xml", currentCallReason);
    }

    private static void backupEmail(){
        currentEmail.fromDB();
        Parser.saveObject("email.xml", currentEmail);
    }

    private static void backupEmailReasons(){
        currentEmailReason.fromDB();
        Parser.saveObject("email-reasons.xml", currentEmailReason);
    }

    private static void recoveryAll(){
        synchronize();
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
        Parser.getObject("call.xml", new STC_Call(true)).toDB(currentCall);
        currentCall.fromDB();
    }

    private static void recoveryCallReasons(){
        Parser.getObject("call-reasons.xml", new STC_CallReason(true)).toDB(currentCallReason);
        currentCallReason.fromDB();
    }

    private static void recoveryEmail(){
        Parser.getObject("email.xml", new STC_Email(true)).toDB(currentEmail);
        currentEmail.fromDB();
    }

    private static void recoveryEmailReasons(){
        Parser.getObject("email-reasons.xml", new STC_EmailReason(true)).toDB(currentEmailReason);
        currentEmailReason.fromDB();
    }

    public static void synchronize(){
        currentCall.fromDB();
        currentCallReason.fromDB();
        currentEmail.fromDB();
        currentEmailReason.fromDB();
    }

    public static STC_Call getCalls(){
        return currentCall;
    }

    public static STC_CallReason getCallReasons(){
        return currentCallReason;
    }

    public static STC_Email getEmails(){
        return currentEmail;
    }

    public static STC_EmailReason getEmailReasons(){
        return currentEmailReason;
    }
}
