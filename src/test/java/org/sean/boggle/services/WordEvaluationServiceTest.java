package org.sean.boggle.services;

import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by seansinnott on 2/26/17.
 */
public class WordEvaluationServiceTest {

    private WordEvaluationService wordEvaluationService;

    public WordEvaluationServiceTest() throws IOException {
        List<String> words = Resources.readLines(Resources.getResource("dictionary.txt"), Charset.defaultCharset());
        wordEvaluationService = new WordEvaluationService(words);
    }

    @Test
    public void evaluate() throws Exception {
        assertEquals(WordEvaluationService.Evaluation.POSSIBILITY_AND_SUCCESS, wordEvaluationService.evaluate("and"));
        assertEquals(WordEvaluationService.Evaluation.SUCCESS, wordEvaluationService.evaluate("abactinally"));
        assertEquals(WordEvaluationService.Evaluation.DEAD_END, wordEvaluationService.evaluate("njkfaf"));
        assertEquals(WordEvaluationService.Evaluation.POSSIBILITY, wordEvaluationService.evaluate("a"));
    }

}