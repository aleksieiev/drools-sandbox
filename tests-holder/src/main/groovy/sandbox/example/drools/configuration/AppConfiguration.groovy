package sandbox.example.drools.configuration

import freemarker.template.Template
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.PathMatchingResourcePatternResolver

@Configuration
class AppConfiguration {
    public static final String TEMPLATES_FOLDER_PATH = '/templates/'
    private static final String SCENARIO_LIST_TEMPLATE_NAME = 'scenario.ftl'

    @Bean
    freemarker.template.Configuration freemarkerConfiguration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration()
        configuration.setDefaultEncoding('UTF-8')
        configuration.setClassForTemplateLoading(AppConfiguration.class, TEMPLATES_FOLDER_PATH)

        return configuration
    }

    @Bean
    Template template() {
        freemarkerConfiguration().getTemplate(SCENARIO_LIST_TEMPLATE_NAME)
    }

    @Bean
    PathMatchingResourcePatternResolver pathResolver() {
        new PathMatchingResourcePatternResolver()
    }
}
