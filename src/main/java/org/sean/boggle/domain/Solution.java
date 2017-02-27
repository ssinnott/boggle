package org.sean.boggle.domain;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by seansinnott on 2/26/17.
 */
public class Solution {

    private List<Word> wordsList;

    public Solution(Collection<Word> wordsList){
        this.wordsList = newArrayList(wordsList);
    }


    public List<Word> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<Word> wordsList) {
        this.wordsList = wordsList;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "wordsList=" + wordsList +
                '}';
    }
}
