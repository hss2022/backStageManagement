package com.hss;

import org.junit.jupiter.api.Test;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.Collections;
public class FastGenerator {
    @Test
    public void fastTest(){

        //放在测试类中执行生成就行
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/diploma?charset=utf-8&userSSL=false", "root", "123456")
//                    生成全局配置
                .globalConfig(builder -> {
                    builder.author("hss") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("F://FastGenerator"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.hss") // 设置父包名
                            // 当前生成的代码全在com.hss.mybatisPlus下
                            .moduleName("mybatisPlus") // 设置父包模块名
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "F://FastGenerator"));
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("client","dish1","menu1","order1","restaurant","sales1") // 设置需要生成的表名
                            .addTablePrefix(""); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板，所以要导入freemarker依赖
                .execute();
    }
}
