package com.suman.matrix;

public class MatrixTest {

	public static void main(String[] args) {
		final int SIZE = 9;
		int[][] matrix = new int[SIZE][SIZE];
		SprialMatrix sprialMatrix = new SprialMatrix.Clockwise(matrix);
		int startValue = 1;
		while (sprialMatrix.hasNext()) {
			sprialMatrix.next(startValue++);
		}
		sprialMatrix.reset();
		/*
		 * while (sprialMatrix.hasNext()) { System.out.print(sprialMatrix.next()
		 * + " "); }
		 */
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.printf("%2d ", matrix[i][j]);
			}
			System.out.println();
		}
	}

}
