package lab01;

public class Step07 {
    // Step 07: enumerations

    enum EngineType {
        GASOLINE, ELECTRIC, HYBRID
    }

    static class Engine {
        private final EngineType type;
        private final int horsepower;

        Engine(EngineType type, int horsepower) {
            this.type = type;
            this.horsepower = horsepower;
        }

        void start() {
            System.out.println(type + " engine started with " + horsepower + " hp.");
        }
    }

    static class Car {
        private static int numberOfCarsCreated = 0;

        private final String model;
        private final Engine engine;

        Car(String model, Engine engine) {
            this.model = model;
            this.engine = engine;
            numberOfCarsCreated++;
        }

        void drive() {
            engine.start();
            System.out.println(model + " is driving.");
        }

        static int getNumberOfCarsCreated() {
            return numberOfCarsCreated;
        }
    }

    public static void main(String[] args) {
        Car car = new Car("EcoRide", new Engine(EngineType.ELECTRIC, 200));
        car.drive();

        System.out.println("Cars created: " + Car.getNumberOfCarsCreated());
    }
}
