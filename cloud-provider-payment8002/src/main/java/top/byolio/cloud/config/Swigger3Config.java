package top.byolio.cloud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: swigger3Config
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/23 10:28
 * @Version 1.0
 */
@Configuration
public class Swigger3Config {
    @Bean
    public GroupedOpenApi payApi() {
        return GroupedOpenApi.builder().group("支付服务模块").pathsToMatch("/pay/**").build();
    }

    @Bean
    public GroupedOpenApi otherApi() {
        return GroupedOpenApi.builder().group("其他服务模块").pathsToMatch("/other/**",
                "/others").build();
    }

    @Bean
    public OpenAPI docsOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("cloud2024")
                        .description("通用rest").version("1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("www.byolio.top")
                        .url("https://www.byolio.top"));
    }
}
