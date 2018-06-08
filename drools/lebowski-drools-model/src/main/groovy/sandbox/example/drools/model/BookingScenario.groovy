package sandbox.example.drools.model

class BookingScenario implements StepEventProducer {
    protected BasicScenario scenario
    private static final String ORDER_TYPE = "order type"

    BookingScenario(final BasicScenario basicScenario) {
        this.scenario = basicScenario
    }

    Boolean containsConditions() {
        true
    }

    String getOrderType() {
        getConditions().get(ORDER_TYPE, "")
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