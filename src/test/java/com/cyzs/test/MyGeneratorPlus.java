package com.cyzs.test;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author xiaoH
 * @create 2019-06-17-18:15
 */
public class MyGeneratorPlus {

    @Test
    public void generator(){

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("D:/IDEAproject/mybatisplus1/src/main/java")
                .setFileOverride(true).setServiceName("%sService")
                .setIdType(IdType.AUTO)
                .setBaseResultMap(true)
                .setBaseColumnList(true);
        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql:///mybatisplus")
                .setUsername("root").setPassword("20131102");
        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true).setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("tb_");
        //包策略
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.cyzs")
                .setController("controller").setService("service")
                .setMapper("mapper")
                .setXml("mapper");

        //
        TemplateConfig templateConfig = new TemplateConfig();
        String templatePath = "/resourves/templates/mapper.xml.vm";
        templateConfig.setXml(templatePath);


        AutoGenerator autoGenerator = new AutoGenerator().setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);
        autoGenerator.execute();

    }
}
