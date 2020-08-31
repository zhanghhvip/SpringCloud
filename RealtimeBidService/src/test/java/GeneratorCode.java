import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class GeneratorCode {
    @Autowired
    private RestTemplate restTemplate;
    @Test
    public void testGenerator(){
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("D:\\WorkPlaces\\IDEA\\springcloud_demo1\\RealtimeBidService\\src\\main\\java");
        gc.setAuthor("zhh");
        gc.setOpen(false);
        gc .setFileOverride(true);//文件覆盖
        gc.setIdType(IdType.AUTO);//主键策略
        gc.setServiceName("%sService");//设置生成的service 接口的名字首字母是否为I INameService
        gc.setBaseResultMap(true);//基本的resultmap
        gc.setBaseColumnList(true);//基本的列集合
        // gc.setSwagger2(true); 实体属性 Swagger2 注解

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/stock_data?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");

        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.aihaokeji");
        pc.setMapper("mapper");
        pc.setXml("mapper");
        pc.setService("service");
        pc.setController("controller");
        pc.setEntity("entity");

        mpg.setPackageInfo(pc);



        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setCapitalMode(true);//开启全局大写命名
        strategy  .setTablePrefix("table_");//表明前缀
        strategy .setInclude("table_realtime_bid");//生成的表

        mpg.setStrategy(strategy);
        mpg.execute();
    }
    @Test
    public void test2(){
        List list1 = new ArrayList(1021);
        for (int i=0;i<1021;i++){
            list1.add(i);
        }
        while(list1.size()>0){
            List list2 = list1.subList(0,list1.size()>500?500:list1.size());
            System.out.println("list2: "+list2);
            list1 = list1.subList(list2.size(),list1.size());

            }


        }


    }


