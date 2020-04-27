package com.dbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.mapper.DbsExamSchemeStudentQuestionsMapper;
import com.dbs.model.DbsExamSchemeStudentQuestions;
import com.dbs.service.SysCpService;
import com.dbs.service.impl.ISysCpService;



@RestController
public class testController {
	@Value("${north.datasource.url}")
	
	private String tp;
	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp = tp;
	}
	@Autowired
	private DbsExamSchemeStudentQuestionsMapper dbsExamSchemeStudentQuestionsMapper;
	@Autowired
	private ISysCpService sysCpService;
	
	@RequestMapping(value = "/testdata")
	public List<DbsExamSchemeStudentQuestions> test() {
		List<DbsExamSchemeStudentQuestions> list = dbsExamSchemeStudentQuestionsMapper.findAdmin();
		DbsExamSchemeStudentQuestions   dd= new DbsExamSchemeStudentQuestions();
		dd.setQuestionsId("5555");
		list.add(dd);
		return list;
	}

	@RequestMapping(value = "/hello")
	public String hello(){
		return "hello";
	}

}
