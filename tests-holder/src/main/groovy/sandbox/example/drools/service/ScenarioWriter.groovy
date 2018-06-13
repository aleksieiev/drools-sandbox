package sandbox.example.drools.service

import freemarker.template.Template
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sandbox.example.drools.model.BasicScenario
import sandbox.example.drools.util.FileUtils
import sandbox.example.drools.util.GlobalProperties

/**
 * Class represents abstraction for any write operations for test scenarios.
 */
@Service
class ScenarioWriter {
    @Autowired
    GlobalProperties globalProperties
    @Autowired
    Template template
    @Autowired
    FileUtils fileUtils

    void saveStories(final List<BasicScenario> scenarios) {
        fileUtils.clearPreviousStories(globalProperties.getServiceName())

        scenarios.stream()
                .map({ processTemplate(template, it) })
                .eachWithIndex { String scenarioText, int index ->
            fileUtils.writeStory(globalProperties.getServiceName(), index.toString(), scenarioText)
        }
    }

    String processTemplate(final Template template, final def scenario) {
        StringWriter stringWriter = new StringWriter()

        Map<String, Object> input = new HashMap<String, Object>()
        input.put("scenario", scenario)
        template.process(input, stringWriter)

        stringWriter.toString()
    }
}
