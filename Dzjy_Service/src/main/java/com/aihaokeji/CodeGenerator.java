package com.aihaokeji;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {
    public static void main(String[] args) {
        //1、全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)//是否支持AR模式
                .setAuthor("zhh")//设置作者
                .setOutputDir("D:\\WorkPlaces\\IDEA\\springcloud_demo1\\Dzjy_Service\\src\\main\\java")//生成路径
                .setFileOverride(true)//文件覆盖
                .setIdType(IdType.AUTO)//主键策略
                .setServiceName("%sService")//设置生成的service 接口的名字首字母是否为I INameService
                .setBaseResultMap(true)//基本的resultmap
                .setBaseColumnList(true);//基本的列集合

        //2、数据原配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)//设置数据库类型
                .setDriverName("com.mysql.jdbc.Driver")//设置数据库驱动
                .setUrl("jdbc:mysql://localhost:3306/stock_data?useUnicode=true&characterEncoding=utf8&useSSL=false")//设置数据库连接
                .setUsername("root")//设置用户名
                .setPassword("123456");//设置密码
        //3、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)//开启全局大写命名
                .setNaming(NamingStrategy.underline_to_camel)//指定表明 字段
//        .setColumnNaming()
                .setNaming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略
        .setTablePrefix("table_")//表明前缀
                .setInclude("table_dzjy");//生成的表
        //4、包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.aihaokeji")//父包
                .setMapper("mapper")
//                .setXml("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("entity");
        // 5、 整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);
        //6.执行
        autoGenerator.execute();
    }
}
