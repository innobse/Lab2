package com.innobse.lab1;

import com.innobse.lab1.DBHelper.DBHelper;
import com.innobse.lab1.DBHelper.models.STC_Call;
import com.innobse.lab1.DBHelper.models.STC_CallReason;
import com.innobse.lab1.JAXB.Parser;

/**
 * Created by bse71 on 21.02.2017.
 */
public class Main {
    public static void main(String[] args) {
        //Connection conn = DB_STC.getConnection();

//        STC_Call call = new STC_Call();
//        Parser.saveObject("file.xml", call);
//
//        STC_Call calls = Parser.getObject("file.xml", new STC_Call());
//        calls.toDB();

        STC_CallReason callR = new STC_CallReason();
        Parser.saveObject("file.xml", callR);

        STC_CallReason callRs = Parser.getObject("file.xml", new STC_CallReason());
        callRs.toDB();

    }
}
