package sandbox.example.drools.model

class DeliveryScenario implements StepEventProducer {
    protected BasicScenario scenario
    private static final String ORDER_TYPE = "order type"
    private static final String DESTINATION = "destination"

    DeliveryScenario(final BasicScenario basicScenario) {
        this.scenario = basicScenario
    }

    Boolean containsConditions() {
        true
    }

    String getOrderType() {
        getConditions().get(ORDER_TYPE, "")
    }

    String getDestination() {
        getConditions().get(DESTINATION, "")
    }

    Map<String, String> getConditions() {
        scenario.getConditions()
    }

    String get(final String paramName){
        getConditions().get(paramName, "")
    }

    void addStep(final String step) {
        scenario.addStep(step)
    }
}