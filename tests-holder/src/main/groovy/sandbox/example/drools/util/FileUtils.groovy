package sandbox.example.drools.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Component

/**
 * Class represents abstraction for any file I/O operations.
 */
@Component
class FileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils)
    @Autowired
    PathMatchingResourcePatternResolver pathResolver
    private static final String STORY_DIR_BASE_PATH = 'stories'

    /**
     * Method save generated story into file.
     * Save path pattern: %BASE_DIR%/stories/{serviceName}/{fileName}.story
     *
     * @param serviceName directory which represents service name
     * @param fileName story file name
     * @param text story text
     */
    void writeStory(final String serviceName, final String fileName, final String text) {
        if (Objects.isNull(text) || text.isEmpty())
            return

        File dir = new File("${FileUtils.getClassLoader().getResource("").path}/$STORY_DIR_BASE_PATH/$serviceName")
        if (!dir.exists())
            dir.mkdirs()

        File file = new File(dir, "${fileName}.story")
        LOGGER.info "Save story to: [${file.getPath()}]"
        file.write(text, "UTF-8")
    }

    /**
     * Method remove directory with previous generated stories for selected service.
     *
     * @param serviceName directory where stories has been saved
     */
    void clearPreviousStories(final String serviceName) {
        Resource resource = pathResolver.getResource("classpath:$STORY_DIR_BASE_PATH/$serviceName")
        if (resource.exists() && resource.getFile().isDirectory())
            resource.getFile().deleteDir()
    }
}
