/** 
 * <pre>项目名称:maven2 
 * 文件名称:TreeController.java 
 * 包名:com.jk.controller 
 * 创建日期:2018年12月29日下午7:54:11 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.lenovo.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import com.lenovo.pojo.Bootstr;
import com.lenovo.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class TreeController {
	  @Autowired
    private TreeService treeService;
	  @ResponseBody
	  @RequestMapping("getTreeBoot")
	  public List <Bootstr> getTreeBoot(){
		  
    	  return treeService.getTreeBoot();
	  }
	  

}
