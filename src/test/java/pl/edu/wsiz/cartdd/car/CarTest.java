package pl.edu.wsiz.cartdd.car;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void shouldRefuelCar() {
        //given
        var car = new Car(Car.Color.BLACK, Car.Make.FORD, BigDecimal.valueOf(10.0), 40);
        var fuelLitres = 20;

        //when
        car.refuel(fuelLitres);

        //then
        Assertions.assertThat(car.getFuelLevel()).isPositive();
    }

    @Test
    void shouldNotRefuelWhenNumberOfLitresIsNegative() {
        //given
        var car = new Car(Car.Color.BLACK, Car.Make.FORD, BigDecimal.valueOf(10.0), 40);
        var fuelLitres = -20;

        //when
        //then
        Assertions.assertThatThrownBy(() -> car.refuel(fuelLitres)).hasMessageContaining("Negative litres not allowed!");
    }

    @Test
    void shouldRefuelNoMoreThanTankCapacity() {
        //given
        var tankCapacity = 40;
        var car = new Car(Car.Color.BLACK, Car.Make.FORD, BigDecimal.valueOf(10.0), tankCapacity);
        var fuelLitres = 50;

        //when
        car.refuel(fuelLitres);

        //then
        Assertions.assertThat(car.getFuelLevel()).isEqualTo(tankCapacity);
    }

    @Test
    void shouldDrive() {
        //given
        var tankCapacity = 40;
        var fuelConsumption = BigDecimal.valueOf(10.0);
        var car = new Car(Car.Color.BLACK, Car.Make.FORD, fuelConsumption, tankCapacity);
        var kilometers = 30;
        var dailyOdometerBefore = car.getDailyOdometer();
        var odometerBefore = car.getOdometer();
        var fuelLevelBefore = car.getFuelLevel();

        //when
        car.drive(kilometers);

        //then
        Assertions.assertThat(car.getDailyOdometer()).isGreaterThan(dailyOdometerBefore);
        Assertions.assertThat(car.getOdometer()).isGreaterThan(odometerBefore);
        Assertions.assertThat(car.getFuelLevel()).isGreaterThan(fuelLevelBefore);
    }

    @Test
    void shouldNotAllowToDriveIfFuelLevelIsNotEnough() {
        //given
        var fuelConsumption = BigDecimal.valueOf(10.0);
        var car = new Car(Car.Color.BLACK, Car.Make.FORD, fuelConsumption, 40);
        var kilometers = 500;
        car.refuel(40);

        //when
        //then
        Assertions.assertThatThrownBy(() -> car.drive(kilometers)).hasMessageContaining("Not enough fuel!");
    }

    @Test
    void shouldResetOdometerAfterReachLimit() {
        //given
        var fuelConsumption = BigDecimal.valueOf(1.0);
        var car = new Car(Car.Color.BLACK, Car.Make.FORD, fuelConsumption, 40);
        var kilometers = 10000;
        car.refuel(40);

        //when
        car.drive(kilometers);

        //then
        Assertions.assertThat(car.getOdometer()).isEqualTo(0);
    }

    @Test
    void shouldResetDailyOdometerAfterReachLimit() {
        //given
        var fuelConsumption = BigDecimal.valueOf(1.0);
        var car = new Car(Car.Color.BLACK, Car.Make.FORD, fuelConsumption, 40);
        var kilometers = 1250;
        car.refuel(40);

        //when
        car.drive(kilometers);

        //then
        Assertions.assertThat(car.getDailyOdometer()).isEqualTo(250);
    }
}
