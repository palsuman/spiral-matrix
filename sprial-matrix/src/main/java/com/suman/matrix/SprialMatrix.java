package com.suman.matrix;

import java.util.Iterator;

public abstract class SprialMatrix implements Iterator<Integer> {
	private int[][] matrix;
	private int row;
	private int col;
	private Direction direction;
	private int itemsRemaining;
	private int[] limits = new int[Direction.values().length];

	public SprialMatrix(int[][] matrix, Direction direction) {
		this.matrix = matrix;
		this.direction = direction;
		this.reset();
	}

	public boolean hasNext() {
		return this.itemsRemaining > 0;
	}

	public Integer next() {
		Integer value = null;
		if (hasNext()) {
			this.itemsRemaining--;
			value = this.matrix[this.row][this.col];
			this.advance();
		}
		return value;
	}

	public void reset() {
		this.itemsRemaining = matrix.length * matrix[0].length;
		this.row = matrix.length / 2;
		this.col = matrix[0].length / 2;
		this.limits[Direction.UP.ordinal()] = this.row - 1;
		this.limits[Direction.DOWN.ordinal()] = this.row + 1;
		this.limits[Direction.LEFT.ordinal()] = this.col - 1;
		this.limits[Direction.RIGHT.ordinal()] = this.col + 1;
	}

	public void next(Integer value) {
		if (hasNext()) {
			this.itemsRemaining--;
			this.matrix[this.row][this.col] = value;
			this.advance();
		}
	}

	private void advance() {
		int rowIncr = this.direction.rowIncr;
		int colIncr = this.direction.colIncr;
		if ((rowIncr < 0 && row == limits[Direction.UP.ordinal()])
				|| (rowIncr > 0 && row == limits[Direction.DOWN.ordinal()])
				|| (colIncr < 0 && col == limits[Direction.LEFT.ordinal()])
				|| (colIncr > 0 && col == limits[Direction.RIGHT.ordinal()])) {
			this.limits[this.direction.ordinal()] += this.direction.signum;
			this.direction = this.nextDirection(this.direction);
		}
		this.row += this.direction.rowIncr;
		this.col += this.direction.colIncr;
	}

	protected abstract Direction nextDirection(Direction direction);

	public static class Clockwise extends SprialMatrix {
		public Clockwise(int[][] matrix) {
			super(matrix, Direction.RIGHT);
		}

		@Override
		protected Direction nextDirection(Direction direction) {
			switch (direction) {
			case RIGHT:
				return Direction.DOWN;
			case DOWN:
				return Direction.LEFT;
			case LEFT:
				return Direction.UP;
			case UP:
				return Direction.RIGHT;
			}
			assert false;
			throw new IllegalArgumentException();
		}

	}

	/**
	 * This ENUM is to maintain the direction
	 * 
	 * @author icteas
	 *
	 */
	protected static enum Direction {
		UP(-1, 0), RIGHT(0, +1), DOWN(+1, 0), LEFT(0, -1);

		public final int rowIncr, colIncr;

		/**
		 * +1 if it represents "positive" movement; -1 if "negative" movement
		 */
		public final int signum;

		private Direction(int rowIncr, int colIncr) {
			this.rowIncr = rowIncr;
			this.colIncr = colIncr;
			this.signum = this.rowIncr + this.colIncr;
		}
	}
}
