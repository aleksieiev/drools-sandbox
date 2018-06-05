
Lifecycle:
After:
Scope: SCENARIO
Outcome: ANY
When Quit browser

<#compress>
Scenario: ${scenario.getScenarioName("default name")}
Meta:
<#list scenario.generatedMeta?keys as key>
        @${key} ${scenario.generatedMeta[key]}
</#list>
<#list scenario.generatedMeta?keys as key>
        @${metaGenerator.createKey(key)} ${metaGenerator.createValue(scenario.generatedMeta[key])}
</#list>
${scenario.getScenario()}
</#compress>