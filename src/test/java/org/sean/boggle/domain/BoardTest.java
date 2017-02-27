package org.sean.boggle.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

/**
 * Created by seansinnott on 2/26/17.
 */
public class BoardTest {

    private Board board;

    public BoardTest(){
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
    }

    @Test
    public void getValueAt() throws Exception {
        Assert.assertEquals("a", board.getValueAt(new Point(0, 0)));
        Assert.assertEquals("d", board.getValueAt(new Point(3, 3)));
        Assert.assertEquals("qu", board.getValueAt(new Point(1, 2)));
    }

    @Test
    public void getAdjacency() throws Exception {

        assertThat(
                board.getAdjacency(new Point(0,0)),
                containsInAnyOrder(
                        new Point(1,0),
                        new Point(1,1),
                        new Point(0,1)
                )
        );

        assertThat(
                board.getAdjacency(new Point(3,3)),
                containsInAnyOrder(
                        new Point(2,2),
                        new Point(2,3),
                        new Point(3,2)
                )
        );

        assertThat(
                board.getAdjacency(new Point(2,2)),
                containsInAnyOrder(
                        new Point(1,1),
                        new Point(1,2),
                        new Point(1,3),
                        new Point(2,1),
                        new Point(2,3),
                        new Point(3,1),
                        new Point(3,2),
                        new Point(3,3)
                )
        );

    }

    @Test
    public void getAllPoints() throws Exception {
        assertThat(
                board.getAllPoints(),
                containsInAnyOrder(
                        new Point(0,0),
                        new Point(0,1),
                        new Point(0,2),
                        new Point(0,3),
                        new Point(1,0),
                        new Point(1,1),
                        new Point(1,2),
                        new Point(1,3),
                        new Point(2,0),
                        new Point(2,1),
                        new Point(2,2),
                        new Point(2,3),
                        new Point(3,0),
                        new Point(3,1),
                        new Point(3,2),
                        new Point(3,3)
                )
        );
    }

}