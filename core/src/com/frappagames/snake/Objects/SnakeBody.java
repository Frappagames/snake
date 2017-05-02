package com.frappagames.snake.Objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * The entire snake
 */

public class SnakeBody {
    private static final int START_LENGHT = 5;
    private ArrayList<SnakePart> parts = new ArrayList<SnakePart>();
    private boolean asTurned = false;

    public SnakeBody(SnakePart.Direction direction, int x, int y) {

        // Initialize snake parts from size and direction
        parts.add(new SnakePart(direction, x, y));
        for (int i = 1; i < START_LENGHT; i++) {
            this.addPart();
        }
    }

    public void addPart() {
        SnakePart lastPart = parts.get(parts.size() - 1);
        switch (lastPart.getDirection()) {
            case LEFT:  parts.add(new SnakePart(lastPart.getDirection(), lastPart.getX() + 1, lastPart.getY())); break;
            case RIGHT: parts.add(new SnakePart(lastPart.getDirection(), lastPart.getX() - 1, lastPart.getY())); break;
            case TOP:   parts.add(new SnakePart(lastPart.getDirection(), lastPart.getX(), lastPart.getY() - 1)); break;
            case DOWN:  parts.add(new SnakePart(lastPart.getDirection(), lastPart.getX(), lastPart.getY() + 1)); break;
        }
    }

    public void turn(SnakePart.Direction direction) {
        if (!this.asTurned) {
            SnakePart firstPart = parts.get(0);

            if ((direction == SnakePart.Direction.LEFT && firstPart.getDirection() != SnakePart.Direction.RIGHT)
                || (direction == SnakePart.Direction.RIGHT && firstPart.getDirection() != SnakePart.Direction.LEFT)
                || (direction == SnakePart.Direction.TOP && firstPart.getDirection() != SnakePart.Direction.DOWN)
                || (direction == SnakePart.Direction.DOWN && firstPart.getDirection() != SnakePart.Direction.TOP)
            ) {
                firstPart.setDirection(direction);
                this.asTurned = true;
            }
        }
    }

    public void move() {
        SnakePart firstPart = parts.get(0);
        SnakePart.Direction direction = firstPart.getDirection();

        for (SnakePart part: parts) {
            SnakePart.Direction nextDirection = part.getDirection();
            part.move();
            part.setDirection(direction);
            direction = nextDirection;
        }

        this.asTurned = false;
    }

    public void draw(Batch batch) {
        int partNumber = 0;
        int snakeSize = parts.size();

        for (SnakePart part: parts) {
            partNumber++;

            if (partNumber == 1) {
                part.draw(batch, SnakePart.PartType.HEAD);
            } else if (partNumber == snakeSize) {
                part.draw(batch, SnakePart.PartType.TAIL);
            } else {
                part.draw(batch, SnakePart.PartType.BODY);
            }
        }
    }

    /**
     * Does the snake eat himself ?
     * Check if the snake head overlap his body
     *
     * @return boolean
     */
    public boolean eatHimself() {
        SnakePart head = parts.get(0);

        for (int i = 1; i < parts.size(); i++) {
            if (parts.get(i).getX() == head.getX() && parts.get(i).getY() == head.getY()) {
                return true;
            }
        }

        return false;
    }

    public boolean intersect(int x, int y) {
        SnakePart part = parts.get(0);

        return (part.getX() == x && part.getY() == y);
    }

    public boolean inSnake(int x, int y) {
        for (SnakePart part : parts) {
            if (part.getX() == x && part.getY() == y) return true;
        }

        return false;
    }

    public Vector2 getHeadPosition() {
        SnakePart firstPart = parts.get(0);

        return new Vector2(firstPart.getX(), firstPart.getY());
    }

    public int getSize() {
        return parts.size();
    }
}
