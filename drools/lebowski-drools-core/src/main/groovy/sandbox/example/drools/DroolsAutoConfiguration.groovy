package sandbox.example.drools

import org.drools.compiler.kie.builder.impl.KieBuilderImpl
import org.drools.compiler.kie.builder.impl.MemoryKieModule
import org.kie.api.KieBase
import org.kie.api.KieServices
import org.kie.api.builder.*
import org.kie.api.runtime.KieContainer
import org.kie.internal.io.ResourceFactory
import org.kie.spring.KModuleBeanFactoryPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.core.io.support.ResourcePatternResolver

@Configuration
class DroolsAutoConfiguration {
    private static final String RULES_PATH = "tables/"

    @Bean
    KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem()
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"))
        }
        return kieFileSystem
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver()
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.xls")
    }

    @Bean
    KieContainer kieContainer() throws IOException {
        final KieRepository kieRepository = getKieServices().getRepository()

        kieRepository.addKieModule(new MemoryKieModule() {
            ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId()
            }
        })

        KieBuilderImpl kieBuilder = getKieServices().newKieBuilder(kieFileSystem())
        kieBuilder.buildAll()

        return getKieServices().newKieContainer(kieBuilder.getKieModule().releaseId)
    }

    private KieServices getKieServices() {
        return KieServices.Factory.get()
    }

    @Bean
    KieBase kieBase() throws IOException {
        return kieContainer().getKieBase()
    }

    /*
     *  As http://docs.jboss.org/drools/release/6.2.0.CR1/drools-docs/html/ch.kie.spring.html
     *  mentions: Without the org.kie.spring.KModuleBeanFactoryPostProcessor bean definition,
     *  the kie-spring integration will not work
     */

    @Bean
    KModuleBeanFactoryPostProcessor kiePostProcessor() {
        new KModuleBeanFactoryPostProcessor()
    }
}
