package com.demo;

import java.util.Scanner;

import javax.lang.model.element.Element;

public class App {
	public static void main(String[] args) {
		try {
			int numOfCells = 1, numRows = 0, numColumns = 0;
			Scanner sc = new Scanner(System.in);
			while (numRows * numColumns != numOfCells) {
				System.out.println("Enter the number of cells:");
				numOfCells = sc.nextInt();
				System.out.println("Enter first dimension length:");
				numRows = sc.nextInt();
				System.out.println("Enter second dimension length:");
				numColumns = sc.nextInt();
				sc.nextLine();
			}

			// padding dummy cells on all four sides of the grid and initializing the cells
			Cell cellArray[][] = new Cell[numRows + 2][numColumns + 2];
			for (int i = 0; i < numRows + 2; i++) {
				for (int j = 0; j < numColumns + 2; j++) {
					cellArray[i][j] = new Cell();
				}
			}

			// creating the grid
			for (int i = 1; i < numRows + 1; i++) {
				for (int j = 1; j < numColumns + 1; j++) {
					System.out.println("Enter name for Row " + i + " and Column " + j + ":");
					String name = sc.nextLine();
//				cellArray[i][j] = new Cell();
					cellArray[i][j].setName(name);
					System.out.println("Enter State for Row " + i + " and Column " + j + ": (0:dead, 1:alive)");
					int state = sc.nextInt();
					cellArray[i][j].setState(state);
					sc.nextLine();
				}
			}

			// seting the state of dummy cells to -1
			for (int i = 0; i < numColumns + 2; i++) {
				cellArray[0][i].setState(-1);
				cellArray[numRows + 1][i].setState(-1);
			}

			// seting the state of dummy cells to -1
			for (int i = 0; i < numRows + 2; i++) {
				cellArray[i][0].setState(-1);
				cellArray[i][numColumns + 1].setState(-1);
			}

			// display the grid before the tick
			System.out.println("*************Grid before the tick**********");
			for (int i = 1; i < numRows + 1; i++) {
				for (int j = 1; j < numColumns + 1; j++) {
					System.out.println(cellArray[i][j]);

				}
			}

			// performing the tick

			for (int i = 1; i < numRows + 1; i++) {
				for (int j = 1; j < numColumns + 1; j++) {
					int countLive = 0, countDead = 0, oldValue, newValue;
					oldValue = cellArray[i][j].getState();
					newValue = oldValue;
					int neighbours[][] = { { i - 1, j - 1 }, { i - 1, j }, { i - 1, j + 1 }, { i, j - 1 }, { i, j + 1 },
							{ i + 1, j - 1 }, { i + 1, j }, { i + 1, j + 1 } };
					for (int[] neighbour : neighbours) {
						if (cellArray[neighbour[0]][neighbour[1]].getState() == 0)
							countDead++;
						else if (cellArray[neighbour[0]][neighbour[1]].getState() == 1)
							countLive++;
					}

					if (cellArray[i][j].getState() == 1 && countLive < 2)
						newValue = 0;

					if (cellArray[i][j].getState() == 1 && countLive > 3)
						newValue = 0;

					if (cellArray[i][j].getState() == 1 && (countLive == 2 || countLive == 3))
						newValue = oldValue;

					if (cellArray[i][j].getState() == 0 && countLive == 3)
						newValue = 1;

					if (newValue != oldValue) {
						cellArray[i][j].setState(newValue);
						System.out.println("State of cell with name: " + cellArray[i][j].getName() + " is changed from "
								+ oldValue + " to " + newValue);
					}
				}
			}

			// display the grid after the tick
			System.out.println("*************Grid after the tick**********");
			for (int i = 1; i < numRows + 1; i++) {
				for (int j = 1; j < numColumns + 1; j++) {
					System.out.println(cellArray[i][j]);

				}
			}

			System.out.println("Enter 1 to Search. \nEnter 2 to exit. ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				System.out.println("Enter the name of the cell: ");
				String name = sc.nextLine();
				int found = 0;
				for (int i = 1; i < numRows + 1; i++) {
					for (int j = 1; j < numColumns + 1; j++) {
						if (name.equalsIgnoreCase(cellArray[i][j].getName())) {
							System.out.println("Cell Found\n" + cellArray[i][j]);
							found=1;
						}
					}
				}
				if(found==0)
					System.out.println("No such cell found\nExiting...");
				break;
			case 2:
				System.exit(0);
			default:
				System.out.println("Invalid input\nExiting...");
			}
		} catch (Exception e) {
			System.out.println("Exeption occurred is: " + e + "\nExiting...");
		}
	}
}
