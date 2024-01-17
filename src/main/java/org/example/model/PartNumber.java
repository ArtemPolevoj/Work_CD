package org.example.model;

public class PartNumber implements Comparable<PartNumber>{
    private final String number;
    private double count;
    private final String name;
    private final String type;

    public PartNumber(String number, double count, String name, String type) {
        this.number = number;
        this.count = count;
        this.name = name;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }


    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }

    @Override
    public int compareTo(PartNumber p) {
        return number.compareTo(p.getNumber());
    }

    @Override
    public String toString() {
        return "PartNumber{" +
                "number='" + number + '\'' +
                ", count=" + count +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartNumber that = (PartNumber) o;

        if (Double.compare(count, that.count) != 0) return false;
        if (!number.equals(that.number)) return false;
        if (!name.equals(that.name)) return false;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = number.hashCode();
        temp = Double.doubleToLongBits(count);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
