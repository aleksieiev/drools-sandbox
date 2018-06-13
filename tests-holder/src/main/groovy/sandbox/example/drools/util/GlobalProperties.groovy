package sandbox.example.drools.util

import org.springframework.stereotype.Component

/**
 * Class is responsible for all configuration properties which has bess passed via command line arguments or system properties.
 */
@Component
class GlobalProperties {
    public static final String PROPERTIES_PREFIX = "qaa"
    private final static String QAA_SERVICE_NAME = "${PROPERTIES_PREFIX}.serviceName"

    private Properties qaProperties = new Properties()

    GlobalProperties() {
        System.getenv()
        System.getProperties().findAll { it.key.toString().startsWith PROPERTIES_PREFIX }.each {
            this.qaProperties.put(it.key.toString(), it.value.toString())
        }
    }

    String getServiceName() {
        qaProperties.get(QAA_SERVICE_NAME, "")
    }
}
