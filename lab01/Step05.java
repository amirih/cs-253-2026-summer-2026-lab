package lab01;

public class Step05 {
    // Step 05: polymorphism
    // polymorphism: the ability of different classes to be treated as instances of
    // the same superclass, allowing for flexible code that can work with objects
    // of different types

    static abstract class Shape {
        abstract double area();

        void printArea() {
            System.out.println(getClass().getSimpleName() + " area = " + area());
        }
    }

    static class Circle extends Shape {
        private double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        @Override
        double area() {
            return Math.PI * radius * radius;
        }
    }

    static class Rectangle extends Shape {
        private double width;
        private double height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        double area() {
            return width * height;
        }
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(3),
                new Rectangle(4, 5)
        };

        for (Shape shape : shapes) {
            shape.printArea();
        }
    }
}
