package com.nysit.stay.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nysit.stay.domain.SheTuan;
import com.nysit.stay.domain.Student;
import com.nysit.stay.domain.User;
import com.nysit.stay.service.StService;
import com.nysit.stay.utils.Datas;
import com.nysit.stay.utils.Result;

@RestController
@RequestMapping("/st")
public class StController {

	@Resource
	private StService stService;
	
	@RequestMapping("/enportStudents")
	public void enportStudents(String stid,HttpServletResponse response) throws IOException{
		List<Student> students=stService.findStudentsByStIdNoPage(stid);
		SheTuan st=stService.findStByid(stid);
		
		HSSFWorkbook wb= new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("社团人员单");
		HSSFRow row = null;
		row = sheet.createRow(0);
		row.setHeight((short) (26.25 * 20));
		row.createCell(0).setCellValue(st.getText());
		CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 4);
		sheet.addMergedRegion(rowRegion);
		
		row = sheet.createRow(1); row.setHeight((short) (22.50 * 20));
		row.createCell(0).setCellValue("学号");
		row.createCell(1).setCellValue("姓名");
		row.createCell(2).setCellValue("班级");
		row.createCell(3).setCellValue("电话");
		row.createCell(4).setCellValue("年级");

		
		for (int i = 0; i < students.size(); i++){
			row = sheet.createRow(i + 2);
			Student s = students.get(i);
			row.createCell(0).setCellValue(s.getS_no());
			row.createCell(1).setCellValue(s.getS_name());
			row.createCell(2).setCellValue(s.getS_class());
			row.createCell(3).setCellValue(s.getS_phone());
			row.createCell(4).setCellValue(s.getS_grade());
		}
		sheet.setDefaultRowHeight((short) (16.5 * 20));
		for (int i = 0; i <= 4; i++) {
			sheet.autoSizeColumn(i);
		}
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		OutputStream os = response.getOutputStream();
		response.setHeader("Content-disposition", "attachment;filename=shetuan.xls");
		wb.write(os);
		os.flush();
		os.close();
		wb.close();
	}
	@RequestMapping("/editStInfo")
	public Object editStInfo(SheTuan st){
		Result r=new Result();
		try {
			stService.editStInfo(st);
			r.setSuccess(true);
		} catch (Exception e) {
			r.setSuccess(false);
			e.printStackTrace();
		}
		return r;
	}
	@RequestMapping("/deleteStInfo")
	public Object deleteStInfo(Datas ds){
		Result r=new Result();
		try {
			stService.deleteStInfo(ds);
			r.setSuccess(true);
		} catch (Exception e) {
			r.setSuccess(false);
			e.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping("/addStInfo")
	public Object addStInfo(SheTuan st){
		Result r=new Result();
		try {
			stService.addStInfo(st);
			r.setSuccess(true);
		} catch (Exception e) {
			r.setSuccess(false);
			e.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping("/findsts")
	public Object findsts(Integer page,Integer rows){
		List<SheTuan> datas=stService.findsts(page,rows);
		int total=stService.getCount();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", datas);	
		return map;
	}
	
	@RequestMapping("/editStudent")
	public Object editStudent(Student student){
		Result r=new Result();
		try {
			stService.editStudent(student);
			r.setSuccess(true);
		} catch (Exception e) {
			r.setSuccess(false);
			e.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping("/deleteSt")
	public Object deleteSt(Datas ds){
		Result r=new Result();
		try {
			stService.deleteStudent(ds);
			r.setSuccess(true);
		} catch (Exception e) {
			r.setSuccess(false);
			e.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping("/addStudent")
	public Object addStudent(Student student){
		Result r=new Result();
		try {
			stService.addStudent(student);
			r.setSuccess(true);
		} catch (Exception e) {
			r.setSuccess(false);
			e.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping("/findStudentByStId")
	public Object findStudentByStId(Integer page,Integer rows,Integer stid){
		List<Student> datas=stService.findStudentsByStId(page,rows,stid);
		int total=stService.getCount(stid);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", datas);	
		return map;
	}
	
	@RequestMapping("/findSt_Name")
	public Object findSt_Name(HttpSession session){
		User user=(User) session.getAttribute("user");
		List<SheTuan> sts=stService.findAllStName();
		List<SheTuan> newSheTuans=new ArrayList<SheTuan>();
		String identity=user.getIdentify();
		if(identity.equals("社长")){
			for(SheTuan st:sts){
				if(st.getSz().equals(user.getUsername())){
					newSheTuans.add(st);
					return newSheTuans;
				}
			}
		}	
		return sts;
	}
}
