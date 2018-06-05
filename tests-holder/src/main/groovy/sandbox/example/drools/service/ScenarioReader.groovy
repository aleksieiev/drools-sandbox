package sandbox.example.drools.service

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Service
import sandbox.example.drools.BasicScenario
import sandbox.example.drools.util.GlobalProperties

import java.util.stream.Collectors
import java.util.stream.Stream

@Service
class ScenarioReader {
    @Autowired
    GlobalProperties globalProperties
    @Autowired
    PathMatchingResourcePatternResolver pathResolver
    JsonSlurper jsonSlurper = new JsonSlurper()

    List<BasicScenario> getScenarios() {
        def scenarios = getScenarios(globalProperties.getServiceName())
        scenarios
    }

    protected List<BasicScenario> getScenarios(final String serviceName) {
        Stream.of(serviceName)
                .map({ pathResolver.getResource("classpath:cases/Booking.json") })
                .filter({ it.exists() })
                .map({ (List) jsonSlurper.parse((InputStream) it.inputStream).cases })
                .findAny().orElse(Collections.emptyList())
                .stream().map({ createTestCase(serviceName, it) }).collect(Collectors.toList())
    }

    private BasicScenario createTestCase(final String serviceName, def test) {
        new BasicScenario(serviceName, test.conditions)
    }
}
