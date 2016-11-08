package model;

public interface ICalcModel {

    public abstract void reset();

    public abstract void add(double value);

    public abstract void subtract(double value);

    public abstract void multiply(double value);

    public abstract void divide(double value);

    public abstract double getTotal();

}