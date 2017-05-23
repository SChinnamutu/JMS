package com.perf.blog.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDetails
{
	@XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String department;
    @XmlAttribute
    private String [] editList;
    
	public UserDetails() {
        super();
    }
    
    public UserDetails(String id,String name, String department){
        super();
        this.id = id;
        this.name = name;
        this.department = department;
    }
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
    
    public String[] getEditList() {
		return editList;
	}

	public void setEditList(String[] editList) {
		this.editList = editList;
	}
	
	@Override
    public String toString(){
        return "UserDetails [id=" + id + ",name=" + name + ", department=" + department + "]";
    }
}