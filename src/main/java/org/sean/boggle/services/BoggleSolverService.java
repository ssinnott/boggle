package org.sean.boggle.services;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.sean.boggle.domain.Board;
import org.sean.boggle.domain.Point;
import org.sean.boggle.domain.Solution;
import org.sean.boggle.domain.Word;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by seansinnott on 2/26/17.
 */
public class BoggleSolverService {

    private WordEvaluationService wordEvaluationService;

    public BoggleSolverService(WordEvaluationService wordEvaluationService){
        Preconditions.checkNotNull(wordEvaluationService, "wordEvaluationService must not be null");
        this.wordEvaluationService = wordEvaluationService;
    }

    private static class PossibleWord{

        Point currentPoint;
        StringBuilder wordPart;
        LinkedHashSet <Point> path;

        public PossibleWord(Point otherPoint, String valueAt, PossibleWord possibleWord) {
            currentPoint = otherPoint;
            wordPart = new StringBuilder(possibleWord.wordPart).append(valueAt);
            path = Sets.newLinkedHashSet(possibleWord.path);
            path.add(otherPoint);
        }

        public PossibleWord(Point point, String valueAt) {
            currentPoint = point;
            wordPart = new StringBuilder(valueAt);
            path = new LinkedHashSet<>();
            path.add(point);
        }
    }

    private List <Word> findAllWords(PossibleWord possibleWord, Board board){

        WordEvaluationService.Evaluation evaluation = wordEvaluationService.evaluate(possibleWord.wordPart.toString());
        List <Word> result = new ArrayList<>();
        switch (evaluation){
            case SUCCESS:
                result.add(new Word(possibleWord.wordPart.toString(), possibleWord.path));
                break;
            case DEAD_END:
                // do nothing
                break;
            case POSSIBILITY_AND_SUCCESS:
                result.add(new Word(possibleWord.wordPart.toString(), possibleWord.path));
                // intentional pass through
            case POSSIBILITY:
                for(Point otherPoint : board.getAdjacency(possibleWord.currentPoint)){
                    if(!possibleWord.path.contains(otherPoint)){
                        PossibleWord newPossibleWord = new PossibleWord(otherPoint, board.getValueAt(otherPoint), possibleWord);
                        result.addAll(findAllWords(newPossibleWord, board));
                    }
                }
                break;
            default:
                    throw new IllegalStateException("Unhandled evaluation case [" + evaluation + "]");
        }
        return result;
    }

    /**
     * Find possible words that can be formed in the boggle board supplied.
     * @param board The board to solve.
     * @return Possible solutions.
     */
    public Solution findSolutions(Board board){
        LinkedHashSet <Word> wordList = new LinkedHashSet<>();

        for(Point point : board.getAllPoints()){
            PossibleWord word = new PossibleWord(point, board.getValueAt(point));
            wordList.addAll(findAllWords(word, board));
        }

        return new Solution(wordList);
    }
}
