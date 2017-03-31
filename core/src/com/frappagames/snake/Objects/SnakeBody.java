package com.frappagames.snake.Objects;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

/**
 * The entire snake
 */

public class SnakeBody {
    private static final int START_LENGHT = 5;
    private ArrayList<SnakePart> parts = new ArrayList<SnakePart>();

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
        SnakePart firstPart = parts.get(0);

        if (direction == SnakePart.Direction.LEFT && firstPart.getDirection() != SnakePart.Direction.RIGHT) {
            firstPart.setDirection(direction);
        }

        if (direction == SnakePart.Direction.RIGHT && firstPart.getDirection() != SnakePart.Direction.LEFT) {
            firstPart.setDirection(direction);
        }

        if (direction == SnakePart.Direction.TOP && firstPart.getDirection() != SnakePart.Direction.DOWN) {
            firstPart.setDirection(direction);
        }

        if (direction == SnakePart.Direction.DOWN && firstPart.getDirection() != SnakePart.Direction.TOP) {
            firstPart.setDirection(direction);
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
    }

    public void draw(Batch batch) {
        for (SnakePart part: parts) {
            part.draw(batch);
        }
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
}
