package com.innobse.lab1.DBHelper.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bse71 on 21.02.2017.
 */
public interface ITable {

    interface ICortege {
        long getId();
        HashMap<String, Long> getDependencies();
        String getTableName();
    }
}
