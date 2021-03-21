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
}
