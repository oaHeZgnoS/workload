package com.workload.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wl_formula")
public class Formula implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;  //��ʽ���
	private String type;  //��ʽ���ͣ����á�У��ʵϰ��У��ʵϰ�����衢���衢�ϻ�
	private int flag;  //������־��eg,-30Ϊ��30��;30Ϊ��30��
	
	private String ratio;  //�ϰ�ϵ��(��ʽ)
	private float lmt;  //�ϰ�ϵ������
	private String sth;  //����˵����nt-���¿Ρ�nc-�¿��Ρ�redo-���ްࡢdouble-˫��
	private float eratio;  //����ϵ��
	
	private String fml;  //��ʽ

	public Formula() {
		super();
	}

	public Formula(int id, String type, int flag, String ratio, float lmt, String sth, float eratio, String fml) {
		super();
		this.id = id;
		this.type = type;
		this.flag = flag;
		this.ratio = ratio;
		this.lmt = lmt;
		this.sth = sth;
		this.eratio = eratio;
		this.fml = fml;
	}

	@Override
	public String toString() {
		return "Formula [id=" + id + ", type=" + type + ", flag=" + flag + ", ratio=" + ratio + ", lmt=" + lmt
				+ ", sth=" + sth + ", eratio=" + eratio + ", fml=" + fml + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public float getLmt() {
		return lmt;
	}

	public void setLmt(float lmt) {
		this.lmt = lmt;
	}

	public String getSth() {
		return sth;
	}

	public void setSth(String sth) {
		this.sth = sth;
	}

	public float getEratio() {
		return eratio;
	}

	public void setEratio(float eratio) {
		this.eratio = eratio;
	}

	public String getFml() {
		return fml;
	}

	public void setFml(String fml) {
		this.fml = fml;
	}
	
	
	
}
