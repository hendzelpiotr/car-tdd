package pl.edu.wsiz.cartdd.car;

import java.math.BigDecimal;

class Car {

    private Color color;
    private Make make;
    private BigDecimal fuelConsumption;
    private Integer tankCapacity;

    public Car(Color color, Make make, BigDecimal fuelConsumption, Integer tankCapacity) {
        this.color = color;
        this.make = make;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    private enum Color {
        RED, GREEN, ORANGE, BLACK
    }

    private enum Make {
        NISSAN, FORD, MAZDA, TESLA
    }
}
