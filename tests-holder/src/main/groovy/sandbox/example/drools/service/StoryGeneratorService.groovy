package sandbox.example.drools.service

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.kie.api.runtime.StatelessKieSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StoryGeneratorService {
    private static final Log LOGGER = LogFactory.getLog(StoryGeneratorService)
    @Autowired
    ScenarioReader scenarioReader
    @Autowired
    ScenarioWriter scenarioWriter
    @Autowired
    StatelessKieSession kieSession

    void generate() {
        def scenarios = scenarioReader.getScenarios()
        LOGGER.debug("Received ${scenarios.size()} scenarios")
        scenarios.stream().forEach({ kieSession.execute(it) })
        scenarioWriter.saveStories(scenarios)
    }
}
