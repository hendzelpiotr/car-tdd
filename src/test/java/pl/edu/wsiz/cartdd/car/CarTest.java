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
        car.refuel(fuelLitres);

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
}
