package com.doubleskyline.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller公共组件
 *
 * @Auther:
 * @date 2017/11/6
 */
public abstract class BaseController<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());


	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(
//				new SimpleDateFormat("yyyy-MM-dd"), true));
	}

}
