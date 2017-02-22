package com.innobse.lab1.DBHelper.models;

import com.innobse.lab1.DBHelper.DBHelper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by bse71 on 21.02.2017.
 */

@XmlRootElement(name="call-reasons")
public final class STC_CallReason implements ITable {
    private static final String NAME = "call_reason";
    private HashMap<Long, STC_CallReason_Cortege> table;

    public STC_CallReason(){
        fromDB();
    }

    public STC_CallReason(HashMap<Long, STC_CallReason_Cortege> set){
        table = set;
    }

    public void toDB(){
        DBHelper.setAll(NAME, table.values(), STC_CallReason.class);
    }


    public void fromDB(){
        HashSet<STC_CallReason_Cortege> hs = new HashSet<>(50);
        DBHelper.getAll(NAME, hs, STC_CallReason_Cortege.class);
        table = new HashMap<>(hs.size());
        for (STC_CallReason_Cortege h : hs) {
            table.put(h.id, h);
        }
    }

    public STC_CallReason_Cortege getReason(long id){
        return table.get(id);
    }

    @XmlElement(name = "call-reason")
    public void setTable(HashMap<Long, STC_CallReason_Cortege> list) {
        table = list;

    }

    public HashMap<Long, STC_CallReason_Cortege> getTable() {
        return table;
    }

    @XmlRootElement
    public static final class STC_CallReason_Cortege implements ICortege {
        private long id;
        private String name;
        private String script;

        public STC_CallReason_Cortege(long id, String name, String script) {
            this.id = id;
            this.name = name;
            this.script = script;
        }

        public STC_CallReason_Cortege(){

        }

        public long getId() {
            return id;
        }

        @XmlAttribute
        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        @XmlElement
        public void setName(String name) {
            this.name = name;
        }

        public String getScript() {
            return script;
        }

        @XmlElement
        public void setScript(String script) {
            this.script = script;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            STC_CallReason_Cortege that = (STC_CallReason_Cortege) o;

            if (id != that.id) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return script != null ? script.equals(that.script) : that.script == null;
        }

        @Override
        public int hashCode() {
            return (int) id;
        }
    }




}
