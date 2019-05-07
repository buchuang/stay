package com.nysit.stay.domain;

public class AppHistory {

	private Integer id;
	private String s_no;
	private String s_name;
	private String s_reason;
	private String spr_name;
	private String sp_remark;
	private String sp_status;
	private String taskname;
	private Integer stayid;
	private Integer userid;
	private String createtime;
	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getStayid() {
		return stayid;
	}
	public void setStayid(Integer stayid) {
		this.stayid = stayid;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getSp_status() {
		return sp_status;
	}
	public void setSp_status(String sp_status) {
		this.sp_status = sp_status;
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
	public String getS_reason() {
		return s_reason;
	}
	public void setS_reason(String s_reason) {
		this.s_reason = s_reason;
	}
	public String getSpr_name() {
		return spr_name;
	}
	public void setSpr_name(String spr_name) {
		this.spr_name = spr_name;
	}
	public String getSp_remark() {
		return sp_remark;
	}
	public void setSp_remark(String sp_remark) {
		this.sp_remark = sp_remark;
	}

	
}
