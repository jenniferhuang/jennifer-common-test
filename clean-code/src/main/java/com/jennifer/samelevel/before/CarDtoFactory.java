package com.jennifer.samelevel.before;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by com.jennifer.huang
 */
public class CarDtoFactory {


    public List<CarDto> create(List<Car> cars) {
        return cars.stream()
                .map(car -> {
                        CarDto carDto = new CarDto();
        carDto.setHorsePower(car.calculateHorsePower());
        return carDto;
            })
            .collect(Collectors.toList());
    }

    private class Car {
        int calculateHorsePower() {
            return 200;
        }
    }

    private class CarDto {
        int horsePower;

        void setHorsePower(int hp) {
            this.horsePower = hp;
        }
    }
}
