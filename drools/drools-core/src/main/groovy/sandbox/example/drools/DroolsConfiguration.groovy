package sandbox.example.drools

import org.kie.api.KieServices
import org.kie.api.runtime.KieContainer
import org.kie.api.runtime.StatelessKieSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import sandbox.example.drools.components.DroolsRuntimeComponent

@Configuration
@ComponentScan
class DroolsConfiguration {

    @Autowired
    List<DroolsRuntimeComponent> droolsComponents

    @Bean
    KieContainer getKieContainer() {
        KieServices kieServices = KieServices.Factory.get()
        KieContainer container = kieServices.getKieClasspathContainer()
        container
    }

    @Bean(name = "statelessKieSession")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    StatelessKieSession getKieSession() {
        StatelessKieSession session = getKieContainer().newStatelessKieSession()
        session.setGlobal("ksession", session)
        registerDroolsComponents(session)
        session
    }

    void registerDroolsComponents(final StatelessKieSession session) {
        droolsComponents.forEach({
            session.setGlobal(it.getDroolsRuntimeComponentName(), it)
        })
    }

}
