import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class GeneraterCodeTest {
    @Test
    public void test(){
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("D:\\WorkPlaces\\IDEA\\springcloud_demo1\\StockCodePool\\src\\main\\java");
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
        strategy  .setTablePrefix("stock_");//表明前缀
        strategy .setInclude("stock_code");//生成的表

        mpg.setStrategy(strategy);
        mpg.execute();

    }
    @Test
    public void test02(){

        List<Long> list1 = new ArrayList<>();
        List<Long> list2 = new ArrayList<>();
        list1.add(1L);
        list1.add(2L);
        list1.add(3L);
        list1.add(4L);
        list1.add(5L);
        list2.add(1L);
        list2.add(2L);
//        list1.addAll(list2);
//        List<Long> newList = new ArrayList<>(new HashSet<>(list1));
//        for (Long a : newList) {
//            System.out.println(a);
//        }

        //集合去重
//        Collection newcodes = new ArrayList(list1);
//        Collection oldcodes = new ArrayList(list2);
        list2.retainAll(list1);
        if (list2.size() != 0) {
            list1.removeAll(list2);
        }

        System.out.println(list1);
        System.out.println(list2);
//        newcodes.removeAll(oldcodes);
//        System.out.println(newcodes);
//        System.out.println(oldcodes);
    }
}
