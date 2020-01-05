/** 
 * <pre>项目名称:maven2 
 * 文件名称:Treea.java 
 * 包名:com.jk.pojo 
 * 创建日期:2018年12月29日下午4:29:40 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.lenovo.pojo;

import java.io.Serializable;

import java.util.List;

import lombok.Data;


@Data
public class Treea  implements Serializable {
	private Integer id;

	private Integer pid;

	private String text;

	private String state; //返回给前台的
	
	//private Integer status;//数据库存贮的

	private String url;

	private String iconCls;

	private List<Treea> children;
	
	private boolean checked;
}
