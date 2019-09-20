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
	private int id;  //公式编号
	private String type;  //公式类型：课堂、校内实习、校外实习、课设、毕设、上机
	private int flag;  //人数标志：eg,-30为≤30人;30为＞30人
	
	private String ratio;  //合班系数(公式)
	private float lmt;  //合班系数上限
	private String sth;  //补充说明：nt-开新课、nc-新开课、redo-重修班、double-双语
	private float eratio;  //特殊系数
	
	private String fml;  //公式

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
