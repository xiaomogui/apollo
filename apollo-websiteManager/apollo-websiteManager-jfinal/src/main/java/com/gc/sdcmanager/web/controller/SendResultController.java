package com.gc.sdcmanager.web.controller;

import java.util.Date;

import com.gc.common.DateKit;
import com.gc.jfinal.ext.render.SpiderRender;
import com.gc.sdcmanager.service.SendResultService;
import com.gc.sdcmanager.web.interceptor.AuthInterceptor;
import com.jfinal.aop.Before;

@Before(AuthInterceptor.class)
public class SendResultController extends BaseController {
	public void index() {
		String host = getPara("host");
		Integer status = getParaToInt("status");
		Integer sportsEventsId = getParaToInt("sportsEventsId");
		Date start = getParaToDate("startTime", DateKit.dateFormat, DateKit.getDateZero());
		Date end = getParaToDate("endTime", DateKit.dateFormat, DateKit.getDateLast());
		setAttr("recordPage", SendResultService.service.paginate(host,status, sportsEventsId, start, end, getParaToInt("page", 1), 50));
		keepPara();
		setAttr("startTime", DateKit.formatDate(start, DateKit.dateFormat));
		setAttr("endTime", DateKit.formatDate(end, DateKit.dateFormat));
		setAttr("status", status);
		setAttr("sportsEventsId", sportsEventsId);
		render("list.ftl");
	}
	
	public void delete() {
		SendResultService.service.deleteById(getParaToInt(0));
		render(SpiderRender.success("删除成功"));
	}
}
