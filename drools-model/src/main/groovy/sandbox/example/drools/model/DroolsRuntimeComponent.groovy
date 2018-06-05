package sandbox.example.drools.model

/**
 * Interface uses as a marker for Drools runtime required components which will be added into KieSession as global variables automatically.
 */
interface DroolsRuntimeComponent {

    /**
     * Method returns name of the component which identifies component in KieSession.
     * @return name of the component
     */
    String getDroolsRuntimeComponentName()
}
