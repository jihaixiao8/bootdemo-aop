package cn.com.jd.util;

/**
 * Created by jihaixiao on 2016/11/23.
 */

public class CarMapperTest {


    public static void main(String[] args) {
        Car car = new Car();
        car.setMake("李");
        car.setNumberOfSeats(21);

        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);
        System.out.println(carDto);
    }

    @org.junit.Test
    public void test(){
        Car car = new Car();
        car.setMake("李");
        car.setNumberOfSeats(21);

        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);
        System.out.println(carDto);
    }

}
