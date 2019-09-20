package com.workload.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wl_teacher0")
public class Workload implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;  //ÿ����������Ӧ��id
	private String department;  //��ʦ����ϵ
	private int t_id;  //��ʦ�Ĺ���
	private String t_name;  //��ʦ����
	private String name;  //ÿһ������������
	private String time;  //ÿһ������������ʱ���ܣ�
	private String classes;  //ÿһ������������صİ༶
	private int amount;  //����
	private float wl;  //������ʽ�����Ĺ�����
	private String type;  //������������𣨱��衢ʵϰ�����ۡ����衢������
	private String remark;  //��ע��Ϣ������У������Ϣ��
	private String term;  //ѧ��
	
	
	public Workload() {
	}
	public Workload(int id, String department, int t_id, String t_name, String name, String time, String classes, int amount,
			float wl, String type, String remark, String term) {
		super();
		this.id = id;
		this.department = department;
		this.t_id = t_id;
		this.t_name = t_name;
		this.name = name;
		this.time = time;
		this.classes = classes;
		this.amount = amount;
		this.wl = wl;
		this.type = type;
		this.remark = remark;
		this.term = term;
	}


	@Override
    public String toString() {
        return "Wl_teacher [id=" + id + ", department=" + department + ", t_id=" + t_id
                + ", t_name=" + t_name + ", name=" + name+ ", time=" + time+ 
                ", classes=" + classes+ ", amount=" + amount+ ", wl=" + wl+ 
                ", type=" + type+ ", remark=" + remark + ",term=" + term + "]";
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public int getT_id() {
		return t_id;
	}


	public void setT_id(int t_id) {
		this.t_id = t_id;
	}


	public String getT_name() {
		return t_name;
	}


	public void setT_name(String t_name) {
		this.t_name = t_name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getClasses() {
		return classes;
	}


	public void setClasses(String classes) {
		this.classes = classes;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public float getWl() {
		return wl;
	}


	public void setWl(float wl) {
		this.wl = wl;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getTerm() {
		return term;
	}
	
	public void setTerm(String term) {
		this.term = term;
	}
	
}
