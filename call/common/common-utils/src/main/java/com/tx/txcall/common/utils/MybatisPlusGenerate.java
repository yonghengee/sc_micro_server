package com.tx.txcall.common.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author： wyh
 * @Descrption：
 * @Date： Create in 10:22 2018/8/7
 */
public class MybatisPlusGenerate {

    //生成文件所在项目路径
    private static String baseProjectPath = "d:\\itSupport\\gen";

    //基本包名
    private static String   basePackage = "com.tx.txcall.modules.money_rate.service";
    //作者
    private static String   authorName  = "wyh";
    //要生成的表名
    //    private static String[] tables = {"t_order","attachment","order_append","partner","sys_authority","sys_file","sys_msg," +
    //            "sys_price_rule","sys_role","sys_role_auth","t_user","team","user_bank_card","user_flowlog","user_wallet","company"};
    private static String[] tables      = {"t_money_rate"};
    //table前缀
    private static String   prefix      = "t_";

    //数据库配置四要素
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String url        = "jdbc:mysql://192.168.1.1/gx_call?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&allowMultiQueries=true";
    private static String username   = "root";
    private static String password   = "tx@infosun";

    public static void main(String[] args) throws Exception {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(baseProjectPath);//自定义文件生成的根目录盘符
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor(authorName);
        gc.setActiveRecord(false);
        // 自定义文件夹名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        //	    gc.setXmlName("%sDao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        //	    gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);

        // 数据源配置


        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);//数据库类型
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setUrl(url);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(false);// 全局大写命名 ORACLE 注意
        //        strategy.setTablePrefix(new String[] {"LP_","A_"});// 此处可以修改为您的表前缀(表名以什么开头)
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setDbColumnUnderline(false);
        strategy.setEntityLombokModel(true);
        strategy.setTablePrefix(new String[] {prefix});
        // lp_test,a_settle_a,a_settle_b,a_settle_c
        strategy.setInclude(tables); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        //         strategy.setSuperEntityClass("com.yqh.itsupport.commons.datasource.entity.BaseEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        //         strategy.setSuperMapperClass("com.yqh.itsupport.commons.datasource.dao.BaseDao");
        // 自定义 service 父类
        //         strategy.setSuperServiceClass("com.yqh.itsupport.commons.datasource.service.BaseService");
        // 自定义 service 实现类父类
        //         strategy.setSuperServiceImplClass("com.yqh.itsupport.commons.datasource.service.impl.BaseServiceImpl");
        // 自定义 controller 父类
        //strategy.setSuperControllerClass("core.system.module.controller.IBaseController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        mpg.setStrategy(strategy);


        // 包配置,配置生成文件所在的文件夹
        PackageConfig pc = new PackageConfig();
        pc.setParent(basePackage);//自定义生成的文件位置，例：此文章生成的位置D:\com\sxit\pgc\module\test
        // pc.setModuleName("demo1");
        //dao层所在的文件夹
        pc.setMapper("mapper");
        //xml文件所在文件夹
        pc.setXml("resource.mapper");
        mpg.setPackageInfo(pc);


        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };


        // 自定义 xxList.jsp 生成
        //List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        //focList.add(new FileOutConfig("/template/list.jsp.vm") {
        //@Override
        //public String outputFile(TableInfo tableInfo) {
        // 自定义输入文件名称
        //return "D://my_" + tableInfo.getEntityName() + ".jsp";
        //}
        //});
        // cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);


        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
        // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
//        TemplateConfig tc = new TemplateConfig();
//        tc.setEntity("template/entity.java");
//        tc.setController(null);
//        // tc.setEntity("...");
//        // tc.setMapper("...");
//        //tc.setXml("...");
//        // tc.setService("...");
//        // tc.setServiceImpl("...");
//        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
//        mpg.setTemplate(tc);


        // 执行生成
        mpg.execute();


    }
}
