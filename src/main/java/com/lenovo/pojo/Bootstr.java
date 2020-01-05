/** 
 * <pre>项目名称:maven2 
 * 文件名称:Bootstr.java 
 * 包名:com.jk.pojo 
 * 创建日期:2019年1月3日下午7:05:03 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.lenovo.pojo;

import java.util.List;

import lombok.Data;

@Data
public class Bootstr {
	private Integer id;
	private Integer pid;
	private String text;
	private String icon;
	private String selectedIcon;
	private String color;
	private String backColor;
	private String href;
	private List <Bootstr> nodes;

}
