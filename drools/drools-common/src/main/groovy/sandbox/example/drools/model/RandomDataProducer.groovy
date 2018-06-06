package sandbox.example.drools.model

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Component
import sandbox.example.drools.components.DroolsRuntimeComponent

/**
 * Class provides different kind of random data e.g. random string with specified length, random numbers, etc.
 */
@Component
class RandomDataProducer implements DroolsRuntimeComponent {
    private static final String DROOLS_COMPONENT_NAME = "randomizer"

    private Random random = new Random()

    String getDroolsRuntimeComponentName() {
        DROOLS_COMPONENT_NAME
    }

    /**
     * Method returns random Integer as a string.
     *
     * @param startWith minimal value for random
     * @param endWith inclusive maximum value for random
     * @return Random Integer as a String
     */
    String random(final int startWith, final int endWith) {
        return Integer.toString(random.nextInt(Math.max(startWith, endWith) + 1))
    }

    /**
     * Method generates random string with specified length.
     * By default length is 32 characters.
     *
     * @param length length of generated random string
     * @return generated random string
     */
    String randomString(final int length = 32) {
        RandomStringUtils.randomAlphanumeric(length)
    }
}
