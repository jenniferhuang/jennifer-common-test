package com.jennifer.samelevel.after;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by com.jennifer.huang
 */
public class CarDtoFactory {


    public List<CarDto> create(List<Car> cars) {
        return cars.stream()
                .map(this::convertCarToCarDto)
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


    private CarDto convertCarToCarDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setHorsePower(car.calculateHorsePower());
        return carDto;
    }
}
