package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            int n = scanner.nextInt();
            Fraction[] fractions = new Fraction[n];
            scanner.nextLine();
            for (int i = 0; i < fractions.length;) {
                String s = scanner.nextLine();
                try {
                    fractions[i] = new Fraction(s);
                    i++;
                } catch (ArithmeticException e) {
                    System.out.println(e.getMessage());
                }
            }
            Arrays.sort(fractions, new Comparator<Fraction>() {
                @Override
                public int compare(Fraction o1, Fraction o2) {
                    return -o1.compareTo(o2);
                }
            });
            System.out.println(Arrays.toString(fractions));
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Введите число");
        } catch (NegativeArraySizeException e) {
            System.out.println("Введите положительное число");
        }


    }


}

