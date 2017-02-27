package org.sean.boggle;

import com.google.common.io.Resources;
import org.sean.boggle.services.BoggleSolverService;
import org.sean.boggle.services.WordEvaluationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by seansinnott on 2/25/17.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {};
    }

    @Bean
    public WordEvaluationService wordEvaluationService() throws IOException {
        List<String> words = Resources.readLines(Resources.getResource("dictionary.txt"), Charset.defaultCharset());
        return new WordEvaluationService(words);
    }

    @Bean
    public BoggleSolverService boggleSolverService() throws IOException {
        return new BoggleSolverService(wordEvaluationService());
    }

}
