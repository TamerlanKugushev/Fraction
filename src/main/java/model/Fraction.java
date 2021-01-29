package model;

public class Fraction implements Comparable<Fraction> {
    private int a;
    private int b;

    public Fraction() {
        this.b = 1;
    }

    public Fraction(int a) {
        this.a = a;
        this.b = 1;
    }

    public Fraction(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Знаменатель равен 0");
        }
        this.a = a;
        this.b = b;
        this.reduce();
    }

    public Fraction(String str) {
        String[] strings = str.split("/| ");
        if (strings.length > 0) {
            this.a = Integer.parseInt(strings[0]);
            this.b = 1;
            if (strings.length > 1) {
                this.b = Integer.parseInt(strings[1]);
                if (this.b == 0) {
                    throw new ArithmeticException("Знаменатель равен нулю");
                }
                this.reduce();
            }
        }

    }

    public Fraction(Fraction fraction) {
        this.a = fraction.a;
        this.b = fraction.b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
        this.reduce();
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
        this.reduce();
    }

    /**
     * Реализуйте закрытый метод reduce, который приводит дробь к каноническому представлению.
     * У каждой дроби существует единственное каноническое представление. Каноническое представление –
     * это такое представление a/b, что b>0, НОД(a,b)=1.
     * Другими словами метод должен производить сокращение дроби и приведение ее знаменателя к положительному числу.
     * Произвести вызов данного метода во всех нужных методах
     */
    private void reduce() {
        int nod = gcd(this.a, this.b);
        this.a /= nod;
        this.b /= nod;

        if (this.b < 0) {
            this.a = -this.a;
            this.b = -this.b;
        }
    }

    public int compareTo(int term) {
        return Integer.compare(this.a, term * this.b);
    }

    public int compareTo(double term) {
        return Double.compare((double) this.a / this.b, term);
    }

    /**
     * Определите операции сложения, вычитания, умножения так, чтобы можно было складывать:
     * •	Две дроби (результатом является Fraction)
     * •	Дробь и int (результатом является Fraction)
     * •	Дробь и double (результатом является double)
     */
    public Fraction sum(Fraction other) {
        return new Fraction(this.a * other.b + other.a * this.b, this.b * other.b);
    }

    public Fraction sum(int term) {
        return new Fraction(term * this.b + this.a, this.b);
    }

    public double sum(double term) {
        return term + (double) this.a / this.b;
    }


    public Fraction subtraction(Fraction other) {
        return new Fraction(this.a * other.b - other.a * this.b, this.b * other.b);
    }

    public Fraction substraction(int term) {
        return new Fraction(this.a - term * this.b, this.b);
    }

    public double substraction(double term) {
        return (double) this.a / this.b - term;
    }

    public Fraction multiple(Fraction other) {
        return new Fraction(this.a * other.a, this.b * other.b);
    }

    public Fraction multiple(int term) {
        return new Fraction(this.a * term, this.b);
    }

    public double multiple(double term) {
        return (double) this.a / this.b * term;
    }

    private Fraction reciprocalFraction() {
        return new Fraction(this.b, this.a);
    }

    /**
     * Определить операцию деления на основе операций умножения и получения обратной дроби
     */
    public Fraction divizion(Fraction other) {
        return this.multiple(other.reciprocalFraction());
    }

    /**
     * Определите операции +=, -=, *=, /= для случая, когда правый операнд имеет тип Fraction
     */

    public Fraction sumAsg(Fraction other) {
        Fraction fractionResult = this.sum(other);
        this.a = fractionResult.a;
        this.b = fractionResult.b;
        return this;
    }

    public Fraction substractionAsg(Fraction other) {
        Fraction fractionResult = this.subtraction(other);
        this.a = fractionResult.a;
        this.b = fractionResult.b;
        return this;
    }

    public Fraction multipleAsg(Fraction other) {
        Fraction fractionResult = this.multiple(other);
        this.a = fractionResult.a;
        this.b = fractionResult.b;
        return this;
    }

    public Fraction divizionAsg(Fraction other) {
        Fraction fractionResult = this.divizion(other);
        this.a = fractionResult.a;
        this.b = fractionResult.b;
        return this;
    }

    /**
     * Реализуйте метод compareTo с реализацией интерфейса Comparable для сравнения двух дробей.
     * Сравнения необходимо реализовать для типов int, double, Fraction
     */
    public int compareTo(Fraction other) {
        return Integer.compare(this.a * other.b, other.a * this.b);
    }

    @Override
    public String toString() {
        if (a == 0) return "0";
        else if (b == 1) return "" + a;
        else return a + "/" + b;
    }

    /**
     * Реализуйте метод sum, принимающий на вход массив дробей и производящий их общее сложение
     */
    public static Fraction sum(Fraction[] fractions) {
        Fraction fractionResult = new Fraction();
        for (int i = 0; i < fractions.length; i++) {
            fractionResult.sumAsg(fractions[i]);
        }
        return fractionResult;
    }

    public static Fraction maxFraction(Fraction[] fractions) {
        Fraction fractionResult = new Fraction(Integer.MIN_VALUE);
        for (int i = 0; i < fractions.length; i++) {
            if (fractionResult.compareTo(fractions[i]) < 0) {
                fractionResult = fractions[i];
            }
        }
        return fractionResult;
    }

    private static int gcd(int a, int b) {
        int max = 1;
        a = Math.abs(a);
        b = Math.abs(b);
        for (int i = 1; i <= a; i++) {
            if (a % i == 0 && b % i == 0) {
                max = i;
            }
        }

        return max;
    }

    /**
     * Произвести сортировку массива дробей в порядке возрастания и в порядке убывания
     */


}
