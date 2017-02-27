package org.sean.boggle.domain;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import java.util.LinkedHashSet;
import java.util.List;

import static com.google.common.collect.Sets.newLinkedHashSet;

/**
 * Created by seansinnott on 2/26/17.
 */
public class Word {

    private String word;
    private LinkedHashSet<Point> path;

    public Word(String word, LinkedHashSet <Point> path){
        Preconditions.checkNotNull(word, "word must not be null");
        Preconditions.checkNotNull(path, "path must not be null");
        this.word = word;
        this.path = newLinkedHashSet(path);
    }

    public LinkedHashSet<Point> getPath() {
        return path;
    }

    public void setPath(LinkedHashSet<Point> path) {
        this.path = path;
    }

    public String getWord() {

        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return path.equals(word.path);
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                '}';
    }
}
