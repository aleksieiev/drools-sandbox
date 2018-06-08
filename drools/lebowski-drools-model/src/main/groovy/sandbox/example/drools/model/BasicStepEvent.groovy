package sandbox.example.drools.model

class BasicStepEvent {
    private StepEventProducer producer
    private String eventType
    private String uiSelector
    private String uiValue

    BasicStepEvent(final StepEventProducer producer,
                   final String eventType,
                   final String uiSelector,
                   final String uiValue) {
        this.producer = producer
        this.eventType = eventType ?: ""
        this.uiSelector = uiSelector ?: ""
        this.uiValue = uiValue ?: ""
    }

    StepEventProducer getProducer() {
        return producer
    }

    String getUiSelector() {
        return uiSelector
    }

    String getUiValue() {
        return uiValue
    }

    void setUiValue(final String uiValue) {
        this.uiValue = uiValue
    }

    String getUiElementType() {
        return eventType
    }

    void addStep(final String step) {
        producer?.addStep(replaceStepValues(step))
    }

    private String replaceStepValues(final String step) {
        step.replaceAll("\\{selector}", uiSelector)
                .replaceAll("\\{value}", uiValue)
    }
}
