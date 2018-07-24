package com.imooc.workflow.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @PackageName: com.imooc.workflow.config
 * @CalssName: Swagger2Config
 * @Description: swagger2配置(可以按照包路径或者URL路径分组，这里选择按照包路径分组)
 * @Auther: dcm
 * @Date: 2018-5-31 12:30
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Autowired
    private TypeResolver typeResolver;

    /**
      * @author dcm
      * @date 2018-7-17 15:37
      * @Description: 自定义的公共方法
      * @param: title 标题
      * @param: description 说明
      * @param: teamService 团队支持信息
      * @param: version 版本
      * @return springfox.documentation.service.ApiInfo
      * @throws
      */
    private ApiInfo setApiInfo(String title, String description, String teamService, String version) {
        return new ApiInfoBuilder().title(title).description(description).termsOfServiceUrl(teamService).version(version).build();
    }

    @Bean
    public Docket adminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(setApiInfo("admin-api文档","监狱管理平台(PMP)-workflow模块-api文档","https://blog.csdn.net/xu_san_duo", "1.0"))
//                .alternateTypeRules(
//                    newRule(
//                        typeResolver.resolve(
//                            DeferredResult.class,
//                            typeResolver.resolve(GenericType.class, WildcardType.class)
//                        ),
//                        typeResolver.resolve(WildcardType.class)
//                    )
//                )
                .groupName("admin模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.imooc.workflow.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
