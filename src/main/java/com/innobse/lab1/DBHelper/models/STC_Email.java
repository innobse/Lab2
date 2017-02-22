package com.innobse.lab1.DBHelper.models;

import com.innobse.lab1.DBHelper.DBHelper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by bse71 on 21.02.2017.
 */

@XmlRootElement(name="emails")
//@XmlType(propOrder = {"table"})
public final class STC_Email implements ITable {
    private static final String NAME = "email";
    private ArrayList<STC_Email_Cortege> table;

    public STC_Email(){
        fromDB();
    }

    public void fromDB(){
        ArrayList<STC_Email_Cortege> al = new ArrayList<>(50);
        DBHelper.getAll(NAME, al, STC_Email_Cortege.class);
        setTable(al);
    }

    public STC_Email(ArrayList<STC_Email_Cortege> list){
        table = list;
    }

    public void toDB(){
        DBHelper.setAll(NAME, table, STC_Email.class);
    }

    @XmlElement(name = "email")
    //@XmlElementWrapper
    public void setTable(ArrayList<STC_Email_Cortege> list) {
        table = list;

    }

    public ArrayList<STC_Email_Cortege> getTable() {
        return table;
    }

    @XmlRootElement
    public static final class STC_Email_Cortege implements ICortege {
        private long id;
        private long email_reason_id;
        private long user_id;
        private long superuser_id;
        private Timestamp sended_at;
        private String content;
        private String subject;

        public STC_Email_Cortege(long id, long email_reason_id, long user_id, long superuser_id, Timestamp sended_at, String content, String subject) {
            this.id = id;
            this.email_reason_id = email_reason_id;
            this.user_id = user_id;
            this.superuser_id = superuser_id;
            this.sended_at = sended_at;
            this.content = content;
            this.subject = subject;
        }

        public STC_Email_Cortege(){

        }

        public long getId() {
            return id;
        }

        @XmlAttribute
        public void setId(long id) {
            this.id = id;
        }

        public STC_EmailReason.STC_EmailReason_Cortege getEmail_reason_id() {
            return new STC_EmailReason().getReason(email_reason_id);
        }

        @XmlElement
        public void setEmail_reason_id(STC_EmailReason.STC_EmailReason_Cortege reason) {
            this.email_reason_id = reason.getId();
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

        public long getSended_at() {
            return sended_at.getTime();
        }

        @XmlElement
        public void setSended_at(long sended_at) {
            this.sended_at = new Timestamp(sended_at);
        }

        public String getContent() {
            return content;
        }

        @XmlElement
        public void setContent(String content) {
            this.content = content;
        }

        public String getSubject() {
            return subject;
        }

        @XmlElement
        public void setSubject(String subject) {
            this.subject = subject;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            STC_Email_Cortege that = (STC_Email_Cortege) o;

            if (id != that.id) return false;
            if (email_reason_id != that.email_reason_id) return false;
            if (user_id != that.user_id) return false;
            if (superuser_id != that.superuser_id) return false;
            if (sended_at != null ? !sended_at.equals(that.sended_at) : that.sended_at != null) return false;
            if (content != null ? !content.equals(that.content) : that.content != null) return false;
            return subject != null ? subject.equals(that.subject) : that.subject == null;
        }

        @Override
        public int hashCode() {
            return (int) id;
        }
    }




}
