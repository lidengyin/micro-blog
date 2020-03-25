package cn.hcnet2006.blog.microconsumer1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
//    Docket docket(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.hcnet2006.mango.controller"))
//                .paths(PathSelectors.any())
//                .build().apiInfo(new ApiInfoBuilder()
//                        .description("mango人力资源管理系统测试文档")
//                        .contact(new Contact("李登印",
//                                "http://47.112.132.177:8095/a.html","2743853037@qq.com"))
//
//                        .version("v1.0")
//                        .title("API测试文档")
//                        .license("Apache2.0")
//                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
//                        .build()
//                );
//
//
//    }

    @Bean
    public Docket api() {
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        //添加head参数end


        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("后台接口文档与测试")
                .description("调用server端接口的测试文档与平台")
                .version("1.0.0")
                .termsOfServiceUrl("http://terms-of-services.url")
                //.license("LICENSE")
                //.licenseUrl("http://url-to-license.com")
                .build();
    }

}