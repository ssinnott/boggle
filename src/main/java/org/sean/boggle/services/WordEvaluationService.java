package org.sean.boggle.services;

import groovy.util.Eval;
import org.apache.commons.collections4.trie.PatriciaTrie;

import java.util.List;
import java.util.Map;

/**
 * Created by seansinnott on 2/26/17.
 */
public class WordEvaluationService {

    private PatriciaTrie <String> dict = new PatriciaTrie<>();

    public WordEvaluationService(List<String> wordList){
        for(String word : wordList){
            dict.put(word, word);
        }
    }

    public enum Evaluation{
        SUCCESS,
        DEAD_END,
        POSSIBILITY,
        POSSIBILITY_AND_SUCCESS
    }

    /**
     * Evaluates a partial or completed word based on the likelyhood that it will result in a completed word.
     *
     * @param word The word or partial word to evaluate.
     * @return Whether it's a complete, or partial word (or both).
     */
    public Evaluation evaluate(String word){

        boolean exists = dict.containsKey(word);
        Map<String, String> possibles = dict.prefixMap(word);

        if(exists && possibles.size() > 1 && word.length() >= 3){
            return Evaluation.POSSIBILITY_AND_SUCCESS;
        }else if(exists && word.length() >= 3){
            return Evaluation.SUCCESS;
        }else if(!possibles.isEmpty()){
            return Evaluation.POSSIBILITY;
        }else{
            return Evaluation.DEAD_END;
        }

    }
}
