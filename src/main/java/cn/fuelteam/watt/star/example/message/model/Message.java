package cn.fuelteam.watt.star.example.message.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="message")
public class Message extends Object {
    
	@Id
    private Long id;

    private String title;

    private String context;

    private Date ctime;
    
    public Message() {
		super();
	}

	public Message(Long id, String title, String context, Date ctime) {
		super();
		this.id = id;
		this.title = title;
		this.context = context;
		this.ctime = ctime;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}