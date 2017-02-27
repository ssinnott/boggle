package org.sean.boggle.services;

import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Test;
import org.sean.boggle.domain.Board;
import org.sean.boggle.domain.Solution;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by seansinnott on 2/26/17.
 */
public class BoggleSolverServiceTest {

    private Board board;
    private BoggleSolverService boggleSolverService;

    public BoggleSolverServiceTest() throws IOException {

        board = new Board(
                4,
                4,
                new char[][]{
                        new char[]{'a','b','c','d'},
                        new char[]{'a','b','c','d'},
                        new char[]{'a','q','c','d'},
                        new char[]{'a','b','c','d'}
                }
        );

        List<String> words = Resources.readLines(Resources.getResource("dictionary.txt"), Charset.defaultCharset());
        WordEvaluationService wordEvaluationService = new WordEvaluationService(words);
        boggleSolverService = new BoggleSolverService(wordEvaluationService);

    }

    @Test
    public void findSolutions() throws Exception {
        Solution solutions = boggleSolverService.findSolutions(board);
        Assert.assertEquals(23, solutions.getWordsList().size());
    }

}