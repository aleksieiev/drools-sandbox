package sandbox.example.drools

import org.kie.api.runtime.KieContainer
import org.kie.api.runtime.StatelessKieSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import sandbox.example.drools.components.DroolsRuntimeComponent
import sandbox.example.drools.model.RandomDataProducer

@Configuration
class DroolsConfiguration {

    @Autowired
    List<DroolsRuntimeComponent> droolsComponents
    @Autowired
    KieContainer kieContainer

    @Bean(name = "statelessKieSession")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    StatelessKieSession getKieSession() {
        StatelessKieSession session = kieContainer.newStatelessKieSession()
        session.setGlobal("ksession", session)
        registerDroolsComponents(session)
        session
    }

    private void registerDroolsComponents(final StatelessKieSession session) {
        RandomDataProducer ran = new RandomDataProducer()
        session.setGlobal(ran.getDroolsRuntimeComponentName(), ran)
    }
}
