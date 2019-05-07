package com.nysit.stay.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nysit.stay.utils.Datas;
import com.nysit.stay.utils.Result;


@RestController
@RequestMapping("/proc")
public class ProcessController {

	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	
	@RequestMapping("/loadMonitorImg")
	public void loadMonitorImg(String id,HttpServletResponse response) throws IOException{
		ProcessInstance pi=runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
		BpmnModel model=repositoryService.getBpmnModel(pi.getProcessDefinitionId());
		List<String> ids=runtimeService.getActiveActivityIds(id);
		ProcessDiagramGenerator processDiagramGenerator=new DefaultProcessDiagramGenerator();
		
		//Context.setProcessEngineConfiguration(processEngineConfiguration.getProcessEngineConfiguration());
		InputStream in=processDiagramGenerator.generateDiagram(model, "png", ids,Collections.<String>emptyList(),"宋体","宋体",null,null, 1.0);
		
		OutputStream out=response.getOutputStream();
		int i=0;
		byte[] b = new byte[1024];
		while((i=in.read(b))!=-1){
			out.write(b, 0, i);
		}
		in.close();
	}
	
	
	@RequestMapping("/proc4piList")
	public Object proc4piList(Integer page,Integer rows){
		
		ProcessInstanceQuery piq=runtimeService.createProcessInstanceQuery();
		List<ProcessInstance> pis = piq.listPage((page-1)*rows, rows);
		List<Map<String, Object>> pisList=new ArrayList<Map<String,Object>>();
		for(ProcessInstance pi : pis){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", pi.getProcessInstanceId());
			map.put("piid", pi.getId());
			String pdid=pi.getProcessDefinitionId();
			
			ProcessDefinition pd=
				repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionId(pdid)
					.singleResult();
			map.put("pdname", pd.getName());
			
			String actId=pi.getActivityId();
			
			Task task=
				taskService
					.createTaskQuery()
					.processInstanceId(pi.getId())
					.taskDefinitionKey(actId)
					.singleResult();
			map.put("taskname", task.getName());
			pisList.add(map);
		}
		Long total=piq.count();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", pisList);	
		return map;
	}
	
	@RequestMapping("/loadingImg")
	public void loadingImg(String id, HttpServletResponse response) throws Exception{
		ProcessDefinition processDefinition=
				repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionId(id)
					.singleResult();
		String deploymentId=processDefinition.getDeploymentId();
		String resourceName=processDefinition.getDiagramResourceName();
		
		InputStream in=repositoryService.getResourceAsStream(deploymentId, resourceName);
		OutputStream out=response.getOutputStream();
		int i=0;
		byte[] b = new byte[1024];
		while((i=in.read(b))!=-1){
			out.write(b, 0, i);
		}
		in.close();
	}
	
	@RequestMapping("/deleteProcess")
	public Object deleteProcess(Datas ds){
		Result result=new Result();
		try {
			List<String> deployids=ds.getDeployids();
			for(String deployid : deployids){
				repositoryService.deleteDeployment(deployid,true);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(true);
		}
		
		return result;
	}
	
	
	@RequestMapping("/procList")
	public Object procList(Integer page,Integer rows){
		ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery();
		List<ProcessDefinition> pds=query.listPage((page-1)*rows, rows);
		List<Map<String,Object>> pdsList=new ArrayList<Map<String,Object>>();
		
		for(ProcessDefinition pd : pds){
			Map<String, Object> map=new HashMap<String, Object>();
			
			map.put("id", pd.getId());
			map.put("key", pd.getKey());
			map.put("name", pd.getName());
			map.put("version", pd.getVersion());
			map.put("deployids", pd.getDeploymentId());
			pdsList.add(map);
		}
		Long total=query.count();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", pdsList);	
		return map;
	}
	
	
	@RequestMapping("/deployProcDef")
	public Object deployProcDef(@RequestParam("procDefFile")MultipartFile file){
		Result result=new Result();
		try {
			repositoryService
			.createDeployment()
			.addInputStream(file.getOriginalFilename(), file.getInputStream())
			.deploy();
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
}
