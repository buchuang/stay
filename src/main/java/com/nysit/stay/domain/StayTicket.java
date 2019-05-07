package com.nysit.stay.domain;

public class StayTicket {

	private Integer id;
	private String s_no;
	private String s_name;
	private String s_class;
	private String s_phone;
	private String s_starttime;
	private String s_endtime;
	private String s_ssbh;
	private String s_reason;
	private String s_zdls;
	private String s_status;
	private String createtime;
	private Integer userid;
	private String piid;
	private String username;
	private Integer status;
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getPiid() {
		return piid;
	}
	public void setPiid(String piid) {
		this.piid = piid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getS_ssbh() {
		return s_ssbh;
	}
	public void setS_ssbh(String s_ssbh) {
		this.s_ssbh = s_ssbh;
	}
	public String getS_status() {
		return s_status;
	}
	public void setS_status(String s_status) {
		this.s_status = s_status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getS_no() {
		return s_no;
	}
	public void setS_no(String s_no) {
		this.s_no = s_no;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_class() {
		return s_class;
	}
	public void setS_class(String s_class) {
		this.s_class = s_class;
	}
	public String getS_phone() {
		return s_phone;
	}
	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}
	public String getS_starttime() {
		return s_starttime;
	}
	public void setS_starttime(String s_starttime) {
		this.s_starttime = s_starttime;
	}
	public String getS_endtime() {
		return s_endtime;
	}
	public void setS_endtime(String s_endtime) {
		this.s_endtime = s_endtime;
	}
	public String getS_reason() {
		return s_reason;
	}
	public void setS_reason(String s_reason) {
		this.s_reason = s_reason;
	}
	public String getS_zdls() {
		return s_zdls;
	}
	public void setS_zdls(String s_zdls) {
		this.s_zdls = s_zdls;
	}
	
}
