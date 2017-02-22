package com.innobse.lab1.DBHelper.models;

import com.innobse.lab1.DBHelper.DBHelper;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * Created by bse71 on 21.02.2017.
 */

@XmlRootElement(name="calls")
//@XmlType(propOrder = {"table"})
public final class STC_Call implements ITable {
    private static final String NAME = "call";
    private ArrayList<STC_Call_Cortege> table;

    public STC_Call(){
        fromDB();
    }

    public void fromDB(){
        ArrayList<STC_Call_Cortege> al = new ArrayList<>(50);
        DBHelper.getAll(NAME, al, STC_Call_Cortege.class);
        setTable(al);
    }

    public STC_Call(ArrayList<STC_Call_Cortege> list){
        table = list;
    }

    public void toDB(){
        DBHelper.setAll(NAME, table, STC_Call.class);
    }

    @XmlElement(name = "call")
    //@XmlElementWrapper
    public void setTable(ArrayList<STC_Call_Cortege> list) {
        table = list;

    }

    public ArrayList<STC_Call_Cortege> getTable() {
        return table;
    }

    @XmlRootElement
    //@XmlType(propOrder = {"id","call_reason_id","user_id"})
    public static final class STC_Call_Cortege implements ITable.ICortege {
        private long id;
        private long call_reason_id;
        private long user_id;
        private long superuser_id;
        private Timestamp created_at;
        private short status;

        public STC_Call_Cortege(long id, long call_reason_id, long user_id, long superuser_id, Timestamp created_at, short status) {
            this.id = id;
            this.call_reason_id = call_reason_id;
            this.user_id = user_id;
            this.superuser_id = superuser_id;
            this.created_at = created_at;
            this.status = status;
        }

        public STC_Call_Cortege(){

        }

        public long getId() {
            return id;
        }

        @XmlAttribute
        public void setId(long id) {
            this.id = id;
        }

        public STC_CallReason.STC_CallReason_Cortege getCall_reason_id() {
            return new STC_CallReason().getReason(call_reason_id);
        }

        @XmlElement
        public void setCall_reason_id(STC_CallReason.STC_CallReason_Cortege reason) {
            this.call_reason_id = reason.getId();
        }

        public long getUser_id() {
            return user_id;
        }

        @XmlElement
        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public long getSuperuser_id() {
            return superuser_id;
        }

        @XmlElement
        public void setSuperuser_id(long superuser_id) {
            this.superuser_id = superuser_id;
        }

        public long getCreated_at() {
            return created_at.getTime();
        }

        @XmlElement
        public void setCreated_at(long created_at) {
            this.created_at = new Timestamp(created_at);
        }

        public short getStatus() {
            return status;
        }

        @XmlElement
        public void setStatus(short status) {
            this.status = status;
        }

    }




}
