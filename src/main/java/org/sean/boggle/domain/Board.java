package org.sean.boggle.domain;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by seansinnott on 2/26/17.
 */
public class Board {

    private int width;
    private int height;
    private char [][] characters;

    public Board(){
    }

    public Board(int width, int height, char [][] characters){
        this.width = width;
        this.height = height;
        this.characters = characters;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public char[][] getCharacters() {
        return characters;
    }

    public void setCharacters(char[][] characters) {
        this.characters = characters;
    }

    /**
     * Returns the character located at the point supplied. If value is 'q' then a 'qu' is returned.
     * @param point the point to return on the board.
     * @return
     */
    public String getValueAt(Point point){

        Preconditions.checkNotNull(point, "point must not be null");
        Preconditions.checkArgument(point.getX() < width && point.getX() >= 0, String.format("point.x must be between 0 and %s", Integer.toString(width)));
        Preconditions.checkArgument(point.getY() < width && point.getY() >= 0, String.format("point.y must be between 0 and %s", Integer.toString(height)));

        char value = characters[point.getY()][point.getX()];
        switch (value){
            case 'q': return "qu";
            default: return String.valueOf(value);
        }
    }

    /**
     * Return valid adjacent points to the point supplied.
     * @param point the point in the center
     * @return All of the valid adjacent points.
     */
    public Collection<Point> getAdjacency(Point point){
        Preconditions.checkNotNull(point, "point must not be null");
        Collection <Point> results = new ArrayList<>();
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                int new_x = point.getX() + i;
                int new_y = point.getY() + j;
                if(new_x >= 0 && new_y >= 0 &&
                        (i != 0 || j != 0) &&
                        new_x < width && new_y < height){
                    results.add(new Point(new_x, new_y));
                }
            }
        }
        return results;
    }

    /**
     * Returns all points that exit on the board.
     * @return A collection of all of the valid points.
     */
    public Collection <Point> getAllPoints(){
        Collection <Point> points = new ArrayList<>();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                points.add(new Point(i, j));
            }
        }
        return points;
    }

}
