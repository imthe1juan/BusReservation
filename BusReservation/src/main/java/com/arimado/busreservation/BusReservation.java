/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.arimado.busreservation;

import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class BusReservation {

    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     */
    static int rows = 10;
    static int columns = 4;
    static String[][] seatReservation = new String[rows][columns];
    static String reservedText = " X    ";

    static int[] chosenRow = new int[1];
    static int[] chosenColumn = new int[1];
    static int reserved = 0;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bus Seat Reservation: ");
        System.out.println("--------------------");
        System.out.print("      ");

        for (int i = 0; i < columns; i++) {
            int fixed = i + 1;
            System.out.print("Col " + fixed + " ");
        }

        System.out.println("");

        for (int j = 0; j < rows; j++) {
            int fixed = j + 1;
            System.out.print("Row " + fixed + "  ");

            for (int i = 0; i < columns; i++) {
                seatReservation[j][i] = " *    ";
                System.out.print(" *    ");
                if (i >= columns - 1) {
                    System.out.println("");
                }
            }
        }

        ReserveSeat();
    }

    public static void ReserveSeat() {
        System.out.println("--------------------");
        System.out.println("Reserve a seat");
        chosenRow[reserved] = InputValidNumber("row", true, 11);
        chosenColumn[reserved] = InputValidNumber("column", false, 5);
        System.out.println("Reserved Row: " + chosenRow[reserved] + ", Column: " + chosenColumn[reserved]);
        System.out.println("--------------------");
        System.out.print("      ");

        for (int i = 0; i < columns; i++) {
            int fixed = i + 1;
            System.out.print("Col " + fixed + " ");
        }

        System.out.println("");

        for (int j = 0; j < rows; j++) {
            int fixed = j + 1;
            System.out.print("Row " + fixed + "  ");

            for (int i = 0; i < columns; i++) {
                seatReservation[j][i] = " *    ";

                for (int k = 0; k < chosenRow.length; k++) {
                    seatReservation[chosenRow[k] - 1][chosenColumn[k] - 1] = reservedText;
                }

                System.out.print(seatReservation[j][i]);
                if (i >= columns - 1) {
                    System.out.println("");
                }
            }
        }

        Expand();
        reserved++;
        ReserveSeat();
    }

    public static int InputValidNumber(String name, boolean isRow, int limitation) {
        int number = 11;
        boolean selected = false;
        do {
            System.out.print("Input " + name + " number: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please input a valid " + name + " number");
                scanner.next();
            }
            number = scanner.nextInt();

            if (number > 0 && number < limitation) {
                selected = true;
                System.out.println("--------------------");
                System.out.println("Selected " + name + " " + number);
                System.out.println("--------------------");
            }

            if (number < 0) {
                System.out.println("Negative number detected, program stopped!");
                System.exit(0);
            }

            if (isRow) {
                if (number - 1 > rows - 1) {
                    System.out.print("There is no " + name + " " + number + ", ");
                } else if (number == 0) {
                    System.out.print("There is no " + name + " " + number + ", ");
                }
            } else {
                if (number - 1 > columns - 1) {
                    System.out.print("There is no " + name + " " + number + ", ");
                } else if (number == 0) {
                    System.out.print("There is no " + name + " " + number + ", ");
                }
            }

        } while (!selected);
        return number;
    }

    public static void Expand() {
        int[] newRowArray = new int[chosenRow.length + 1];
        System.arraycopy(chosenRow, 0, newRowArray, 0, chosenRow.length);

        //an alternative to using System.arraycopy would be a for-loop:
        // for(int i = 0; i < OrigArray.length; i++)
        //     newArray[i] = OrigArray[i];
        chosenRow = newRowArray;
        int[] newColumnArray = new int[chosenColumn.length + 1];
        System.arraycopy(chosenColumn, 0, newColumnArray, 0, chosenColumn.length);

        //an alternative to using System.arraycopy would be a for-loop:
        // for(int i = 0; i < OrigArray.length; i++)
        //     newArray[i] = OrigArray[i];
        chosenColumn = newColumnArray;
    }
}
