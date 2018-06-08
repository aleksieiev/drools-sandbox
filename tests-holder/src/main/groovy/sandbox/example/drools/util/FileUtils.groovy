package sandbox.example.drools.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Component

@Component
class FileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils)
    @Autowired
    PathMatchingResourcePatternResolver pathResolver
    private static final String STORY_DIR_BASE_PATH = 'stories'

    void writeStory(final String serviceName, final String fileName, final String text) {
        if (text.size() > 0) {
            File dir = new File("${FileUtils.getClassLoader().getResource("").path}/$STORY_DIR_BASE_PATH/$serviceName")
            if (!dir.exists()) dir.mkdirs()

            File file = new File(dir, "${fileName}.story")
            LOGGER.info "Save story to: [${file.getPath()}]"
            file.write(text, "UTF-8")
        }
    }

    void clearPreviousStories(final String serviceName) {
        Resource resource = pathResolver.getResource("classpath:$STORY_DIR_BASE_PATH/$serviceName")
        if (resource.exists() && resource.getFile().isDirectory())
            resource.getFile().deleteDir()
    }
}
