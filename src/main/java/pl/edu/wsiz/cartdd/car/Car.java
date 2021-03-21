package pl.edu.wsiz.cartdd.car;

import java.math.BigDecimal;

class Car {

    private Color color;
    private Make make;
    private BigDecimal fuelConsumption;
    private Integer tankCapacity;
    private Integer fuelLevel = 0;
    private Integer dailyOdometer = 0;
    private Integer odometer = 0;

    private static final int MAX_ODOMETER = 10000;
    private static final int MAX_DAILY_ODOMETER = 1000;

    public Car(Color color, Make make, BigDecimal fuelConsumption, Integer tankCapacity) {
        this.color = color;
        this.make = make;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public Color getColor() {
        return color;
    }

    public Make getMake() {
        return make;
    }

    public BigDecimal getFuelConsumption() {
        return fuelConsumption;
    }

    public Integer getTankCapacity() {
        return tankCapacity;
    }

    public Integer getFuelLevel() {
        return fuelLevel;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public Integer getDailyOdometer() {
        return dailyOdometer;
    }

    public void refuel(int fuelLitres) {
        if (fuelLitres < 0) {
            throw new RuntimeException("Negative litres not allowed!");
        }

        int litresAfterRefuel = fuelLitres + fuelLevel;
        if (litresAfterRefuel > tankCapacity) {
            fuelLevel = tankCapacity;
        } else {
            fuelLevel = litresAfterRefuel;
        }
    }

    public void drive(int kilometers) {
        var maxAllowedDistance = (fuelLevel * 100) / fuelConsumption.doubleValue();
        if (kilometers > maxAllowedDistance) {
            throw new RuntimeException("Not enough fuel!");
        }

        dailyOdometer += kilometers;
        odometer += kilometers;

        if (dailyOdometer > MAX_DAILY_ODOMETER) {
            dailyOdometer = dailyOdometer - MAX_DAILY_ODOMETER;
        }
        if (odometer > MAX_ODOMETER) {
            odometer = odometer - MAX_ODOMETER;
        }
    }

    public void resetDailyOdometer() {
        dailyOdometer = 0;
    }

    enum Color {
        RED, GREEN, ORANGE, BLACK
    }

    enum Make {
        NISSAN, FORD, MAZDA, TESLA
    }
}
