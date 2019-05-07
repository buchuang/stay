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

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nysit.stay.domain.AppHistory;
import com.nysit.stay.domain.StayTicket;
import com.nysit.stay.domain.User;
import com.nysit.stay.service.AuthHistoryService;
import com.nysit.stay.service.StayTicketService;
import com.nysit.stay.utils.Datas;
import com.nysit.stay.utils.DateUtil;
import com.nysit.stay.utils.Result;

@RestController
@RequestMapping("/stay")
public class StayTicketController {

	@Resource
	private StayTicketService stayTicketService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private AuthHistoryService authHistoryService;
	
	@RequestMapping("/findAllZdls")
	public Object findAllZdls(){
		return stayTicketService.findAllZdls();
	}
	
	
	@RequestMapping("/loadByQuery")
	public Object loadByQuery(String queryValue,Integer page,Integer rows){
		int total=stayTicketService.getCountByQuery(queryValue);
		List<User> datas=stayTicketService.findStayByQuery(page,rows,queryValue);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", datas);	
		return map;
	}
	
	@RequestMapping("/exportSuccessExcel")
	public void exportSuccessExcel(HttpServletResponse response) throws IOException{
		List<StayTicket> datas=stayTicketService.findSuccessSp();
		HSSFWorkbook wb= new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("社团留校单");
		HSSFRow row = null;
		row = sheet.createRow(0);
		row.setHeight((short) (26.25 * 20));
		row.createCell(0).setCellValue("学生留校表");
		CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 8);
		sheet.addMergedRegion(rowRegion);
		
		row = sheet.createRow(1); row.setHeight((short) (22.50 * 20));
		row.createCell(0).setCellValue("学号");
		row.createCell(1).setCellValue("姓名");
		row.createCell(2).setCellValue("手机号");
		row.createCell(3).setCellValue("留校开始时间");
		row.createCell(4).setCellValue("结束时间");
		row.createCell(5).setCellValue("宿舍编号");
		row.createCell(6).setCellValue("留校原因");
		row.createCell(7).setCellValue("审批状态");
		
		for (int i = 0; i < datas.size(); i++){
			row = sheet.createRow(i + 2);
			StayTicket st = datas.get(i);
			row.createCell(0).setCellValue(st.getS_no());
			row.createCell(1).setCellValue(st.getS_name());
			row.createCell(2).setCellValue(st.getS_phone());
			row.createCell(3).setCellValue(st.getS_starttime());
			row.createCell(4).setCellValue(st.getS_endtime());
			row.createCell(5).setCellValue(st.getS_ssbh());
			row.createCell(6).setCellValue(st.getS_reason());
			row.createCell(7).setCellValue(st.getS_status());
		}
		sheet.setDefaultRowHeight((short) (16.5 * 20));
		for (int i = 0; i <= 13; i++) {
			sheet.autoSizeColumn(i);
		}
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		OutputStream os = response.getOutputStream();
		response.setHeader("Content-disposition", "attachment;filename=successStaySchool.xls");
		wb.write(os);
		os.flush();
		os.close();
		wb.close();
	}
	@RequestMapping("/exportErrorExcel")
	public void exportErrorExcel(HttpServletResponse response) throws IOException{
		List<StayTicket> datas=stayTicketService.findErrorSp();
		HSSFWorkbook wb= new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("社团留校单");
		HSSFRow row = null;
		row = sheet.createRow(0);
		row.setHeight((short) (26.25 * 20));
		row.createCell(0).setCellValue("学生留校表");
		CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 8);
		sheet.addMergedRegion(rowRegion);
		
		row = sheet.createRow(1); row.setHeight((short) (22.50 * 20));
		row.createCell(0).setCellValue("学号");
		row.createCell(1).setCellValue("姓名");
		row.createCell(2).setCellValue("手机号");
		row.createCell(3).setCellValue("留校开始时间");
		row.createCell(4).setCellValue("结束时间");
		row.createCell(5).setCellValue("宿舍编号");
		row.createCell(6).setCellValue("留校原因");
		row.createCell(7).setCellValue("审批状态");
		
		for (int i = 0; i < datas.size(); i++){
			row = sheet.createRow(i + 2);
			StayTicket st = datas.get(i);
			row.createCell(0).setCellValue(st.getS_no());
			row.createCell(1).setCellValue(st.getS_name());
			row.createCell(2).setCellValue(st.getS_phone());
			row.createCell(3).setCellValue(st.getS_starttime());
			row.createCell(4).setCellValue(st.getS_endtime());
			row.createCell(5).setCellValue(st.getS_ssbh());
			row.createCell(6).setCellValue(st.getS_reason());
			row.createCell(7).setCellValue(st.getS_status());
		}
		sheet.setDefaultRowHeight((short) (16.5 * 20));
		for (int i = 0; i <= 13; i++) {
			sheet.autoSizeColumn(i);
		}
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		OutputStream os = response.getOutputStream();
		response.setHeader("Content-disposition", "attachment;filename=errorStaySchool.xls");
		wb.write(os);
		os.flush();
		os.close();
		wb.close();
	}
	@RequestMapping("/exportAllExcel")
	public void exportAllExcel(HttpServletResponse response) throws IOException{
		List<StayTicket> datas=stayTicketService.findAllSp();
		HSSFWorkbook wb= new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("社团留校单");
		HSSFRow row = null;
		row = sheet.createRow(0);
	    row.setHeight((short) (26.25 * 20));
	    row.createCell(0).setCellValue("学生留校表");
	    CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 8);
	    sheet.addMergedRegion(rowRegion);
	    
	    row = sheet.createRow(1); row.setHeight((short) (22.50 * 20));
	    row.createCell(0).setCellValue("学号");
	    row.createCell(1).setCellValue("姓名");
	    row.createCell(2).setCellValue("手机号");
	    row.createCell(3).setCellValue("留校开始时间");
	    row.createCell(4).setCellValue("结束时间");
	    row.createCell(5).setCellValue("宿舍编号");
	    row.createCell(6).setCellValue("留校原因");
	    row.createCell(7).setCellValue("审批状态");
	      
	    for (int i = 0; i < datas.size(); i++){
	    	row = sheet.createRow(i + 2);
	    	StayTicket st = datas.get(i);
	    	row.createCell(0).setCellValue(st.getS_no());
	    	row.createCell(1).setCellValue(st.getS_name());
	    	row.createCell(2).setCellValue(st.getS_phone());
	    	row.createCell(3).setCellValue(st.getS_starttime());
	    	row.createCell(4).setCellValue(st.getS_endtime());
	    	row.createCell(5).setCellValue(st.getS_ssbh());
	    	row.createCell(6).setCellValue(st.getS_reason());
	    	row.createCell(7).setCellValue(st.getS_status());
	    }
	    sheet.setDefaultRowHeight((short) (16.5 * 20));
	    for (int i = 0; i <= 13; i++) {
	    	sheet.autoSizeColumn(i);
	    }
	    response.setContentType("application/vnd.ms-excel;charset=utf-8");
	    OutputStream os = response.getOutputStream();
	    response.setHeader("Content-disposition", "attachment;filename=allStaySchool.xls");
	    wb.write(os);
	    os.flush();
	    os.close();
	    wb.close();
	}
	
	@RequestMapping("/allSpList")
	public Object allSpList(Integer page,Integer rows){
		int total=stayTicketService.getAllSpCount();
		List<StayTicket> datas=stayTicketService.findAllSpByPage(page,rows);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", datas);	
		return map;
	}
	
	
	@RequestMapping("/splsList")
	public Object splsList(Integer page,Integer rows,HttpSession session){
		User user=(User)session.getAttribute("user");
		int total=authHistoryService.getSplsCount(user.getId());
		List<AppHistory> datas=authHistoryService.findSplsByPage(page,rows,user.getId());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", datas);	
		return map;
	}
	
	@RequestMapping("/yslxdList")
	public Object yslxdList(Integer page,Integer rows,HttpSession session){
		User user=(User)session.getAttribute("user");
		int total=stayTicketService.getYslxdCount(user.getId());
		List<User> datas=stayTicketService.findYslxdByPage(page,rows,user.getId());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", datas);	
		return map;
	}
	
	
	@RequestMapping("/historyList")
	public Object historyList(Integer id){
		
		List<AppHistory> ah=authHistoryService.findHistoryById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", ah.size());
		map.put("rows", ah);	
		return map;
	}
	
	@RequestMapping("/appAuth")
	public Object appAuth(AppHistory ah,String taskid,String piid,String taskname,HttpSession session,String status){
		Result result=new Result();
		try {
			if(!"驳回".equals(status)){
				
				taskService.complete(taskid);
				
				ah.setStayid(ah.getId());
				ah.setSp_status("同意");
				User user=(User) session.getAttribute("user");
				ah.setUserid(user.getId());
				ah.setTaskname(taskname);
				ah.setSpr_name(user.getUsername());
				ah.setCreatetime(DateUtil.getCurrentTime());
				authHistoryService.addHistory(ah);
				
				StayTicket st=new StayTicket();
				st.setId(ah.getId());
				HistoricProcessInstance hpi=
					historyService
						.createHistoricProcessInstanceQuery()
						.processInstanceId(piid)
						.finished()
						.singleResult();
				if(hpi!=null){
					st.setS_status("审批完成");
					st.setStatus(1);
					stayTicketService.updateAuthStatus(st);
				}
			}else{
				taskService.addComment(taskid, piid, "驳回");
		        runtimeService.deleteProcessInstance(piid,"");
		        historyService.deleteHistoricProcessInstance(piid);
				ah.setStayid(ah.getId());
				ah.setSp_status("不同意");
				User user=(User) session.getAttribute("user");
				ah.setUserid(user.getId());
				ah.setTaskname(taskname);
				ah.setSpr_name(user.getUsername());
				ah.setCreatetime(DateUtil.getCurrentTime());
				authHistoryService.addHistory(ah);
				
				StayTicket st=new StayTicket();
				st.setId(ah.getId());
				st.setS_status("审批失败");
				st.setStatus(1);
				stayTicketService.updateAuthStatus(st);
			}
			
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/authList")
	public Object authList(Integer page,Integer rows,HttpSession session){
		User user=(User)session.getAttribute("user");
		
		List<Task> tasks=
			taskService
				.createTaskQuery()
				.taskAssignee(user.getUsername())
				.listPage((page-1)*rows, rows);
		List<Map<String, Object>> taskList=new ArrayList<Map<String,Object>>();
		for(Task task : tasks){
			Map<String, Object> map=new HashMap<String, Object>();
			
			map.put("taskid", task.getId());
			map.put("taskname", task.getName());
			map.put("tasktime", DateUtil.getCurrentTime());
			String pdid=task.getProcessDefinitionId();
			ProcessDefinition pd=
				repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionId(pdid)
					.singleResult();
			map.put("pdname", pd.getName());
			String piid=task.getProcessInstanceId();
			map.put("piid", piid);
			StayTicket stayTicket=stayTicketService.findBtByPiid(piid);
			map.put("id", stayTicket.getId());
			map.put("s_reason", stayTicket.getS_reason());
			map.put("s_name", stayTicket.getS_name());
			map.put("s_no", stayTicket.getS_no());
			map.put("s_zdls", stayTicket.getS_zdls());
			map.put("username", stayTicket.getUsername());
			map.put("createtime", stayTicket.getCreatetime());
			taskList.add(map);
		}
		Long total=
			taskService
				.createTaskQuery()
				.taskAssignee(user.getUsername())
				.count();
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", taskList);	
		return map;
	}
	
	
	@RequestMapping("/editStatus")
	public Object editStatus(Integer id,String zdls){
		Result result=new Result();
		try {
			ProcessDefinition pd=
				repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionKey("stay")
					.latestVersion()
					.singleResult();
			
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("zdls",zdls);
			ProcessInstance pi=runtimeService.startProcessInstanceById(pd.getId(),map);
			
			StayTicket st=new StayTicket();
			st.setId(id);
			st.setPiid(pi.getId());
			st.setS_status("审批中");
			
			stayTicketService.editStayTicketStatus(st);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/editStayTicket")
	public Object editStayTicket(StayTicket st){
		Result result=new Result();
		try {
			stayTicketService.editStayTicket(st);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/deleteStayTicket")
	public Object deleteStayTicket(Datas ds){
		Result result=new Result();
		try {
			stayTicketService.deleteStayTicket(ds);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/addStayTicket")
	public Object addStayTicket(StayTicket st,HttpSession session){
		Result result=new Result();
		try {
			st.setS_status("待审批");
			st.setStatus(0);
			User user=(User) session.getAttribute("user");
			st.setUserid(user.getId());
			stayTicketService.insertStayTicket(st);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/stayList")
	public Object stayList(Integer page, Integer rows,HttpSession session){
		User user=(User)session.getAttribute("user");
		int total=stayTicketService.getCount(user.getId());
		List<User> datas=stayTicketService.findStayByPage(page,rows,user.getId());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", datas);	
		return map;
	}
}
