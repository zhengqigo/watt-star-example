package cn.fuelteam.watt.star.example.base.model;

import java.util.Date;

import javax.persistence.Table;

@Table(name = "base")
public class Base extends Object {

    private Long id;

    private String name;

    private Date ctime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}