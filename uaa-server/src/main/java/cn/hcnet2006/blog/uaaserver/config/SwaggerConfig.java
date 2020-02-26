package cn.hcnet2006.blog.uaaserver.config;//package cn.hcnet2006.jwt.uaaservice.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.ParameterBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.schema.ModelRef;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Parameter;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//    @Bean
//    public Docket createRestApi(){
//        //添加请求参数，我们把token作为头部参数传入后端
//        ParameterBuilder parameterBuilder = new ParameterBuilder();
//        List<Parameter> parameters = new ArrayList<>();
//        //参数名
//        parameterBuilder.name("Authorization")
//                //参数描述
//                .description("令牌")
//                //模型参考
//                .modelRef(new ModelRef("string"))
//                //参数类型
//                .parameterType("header")
//                //参数构建
//                .build();
//        //添加参数
//        parameters.add(parameterBuilder.build());
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .globalOperationParameters(parameters);
//    }
//
//    private ApiInfo apiInfo(){
//        return  new ApiInfoBuilder().build();
//    }
//}
