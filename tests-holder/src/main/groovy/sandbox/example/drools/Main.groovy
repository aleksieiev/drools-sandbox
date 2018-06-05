package sandbox.example.drools

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import sandbox.example.drools.service.StoryGeneratorService

@SpringBootApplication
@EnableAutoConfiguration
class Main implements ApplicationRunner {

    @Autowired
    StoryGeneratorService storyGeneratorService

    static void main(String[] args) {
        SpringApplication.run(Main, args)
    }

    void run(final ApplicationArguments args) throws Exception {
        storyGeneratorService.generate()
    }
}
