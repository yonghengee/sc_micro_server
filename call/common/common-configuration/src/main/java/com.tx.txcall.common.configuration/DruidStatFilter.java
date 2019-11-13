package com.tx.txcall.common.configuration;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Author: WangRui
 * Date: 2018/5/18
 * Describe: StatFilter 用于统计监控信息
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
})
public class DruidStatFilter extends DruidStatProperties.WebStatFilter {}