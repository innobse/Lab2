package com.innobse.lab1.DBHelper.models;

import com.innobse.lab1.DBHelper.DBHelper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by bse71 on 21.02.2017.
 */

@XmlRootElement(name="email-reasons")
public final class STC_EmailReason implements ITable {
    private static final String NAME = "email_reason";
    private HashMap<Long, STC_EmailReason_Cortege> table;

    public STC_EmailReason(){
        fromDB();
    }

    public STC_EmailReason(HashMap<Long, STC_EmailReason_Cortege> set){
        table = set;
    }

    public void toDB(){
        DBHelper.setAll(NAME, table.values(), STC_EmailReason.class);
    }


    public void fromDB(){
        HashSet<STC_EmailReason_Cortege> hs = new HashSet<>(50);
        DBHelper.getAll(NAME, hs, STC_EmailReason_Cortege.class);
        table = new HashMap<>(hs.size());
        for (STC_EmailReason_Cortege h : hs) {
            table.put(h.id, h);
        }
    }

    public STC_EmailReason_Cortege getReason(long id){
        return table.get(id);
    }

    @XmlElement(name = "email-reason")
    public void setTable(HashMap<Long, STC_EmailReason_Cortege> list) {
        table = list;

    }

    public HashMap<Long, STC_EmailReason_Cortege> getTable() {
        return table;
    }

    @XmlRootElement
    public static final class STC_EmailReason_Cortege implements ICortege {
        private long id;
        private String name;
        private String template;
        private String subject;

        public STC_EmailReason_Cortege(long id, String name, String template, String subject) {
            this.id = id;
            this.name = name;
            this.template = template;
            this.subject = subject;
        }

        public STC_EmailReason_Cortege(){

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

        public String getTemplate() {
            return template;
        }

        @XmlElement
        public void setTemplate(String template) {
            this.template = template;
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

            STC_EmailReason_Cortege that = (STC_EmailReason_Cortege) o;

            if (id != that.id) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            if (template != null ? !template.equals(that.template) : that.template != null) return false;
            return subject != null ? subject.equals(that.subject) : that.subject == null;
        }

        @Override
        public int hashCode() {
            return (int) id;
        }
    }




}
