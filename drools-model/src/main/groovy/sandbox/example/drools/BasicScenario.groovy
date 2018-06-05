package sandbox.example.drools

class BasicScenario {
    private static final String SCENARIO_NAME = "name"

    protected Map<String, String> conditions
    protected Map<String, String> generatedMeta = new HashMap<>()
    protected StringBuffer scenarioSteps = new StringBuffer()
    protected String serviceName

    BasicScenario(final String serviceName, final Map<String, String> conditions) {
        this.serviceName = serviceName
        this.conditions = conditions
    }

    String getScenarioName(final String defaultName = '') {
        conditions.get(SCENARIO_NAME, defaultName)
    }

    void addStep(final String step) {
        if (step && !step.isEmpty()) {
            scenarioSteps.append(step).append(System.lineSeparator())
        }
    }

    String getScenario() {
        scenarioSteps.toString()
    }

    Map<String, String> getGeneratedMeta() {
        generatedMeta
    }
}
