package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("car-1");
        car.setCarName("Sedan");
        car.setCarColor("Black");
        car.setCarQuantity(2);
    }

    @Test
    void testCreate() {
        when(carRepository.create(car)).thenReturn(car);

        Car result = carService.create(car);

        assertSame(car, result);
        verify(carRepository).create(car);
    }

    @Test
    void testFindAll() {
        Car secondCar = new Car();
        secondCar.setCarId("car-2");
        Iterator<Car> iterator = List.of(car, secondCar).iterator();
        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> result = carService.findAll();

        assertEquals(2, result.size());
        assertEquals("car-1", result.get(0).getCarId());
        assertEquals("car-2", result.get(1).getCarId());
        verify(carRepository).findAll();
    }

    @Test
    void testFindById() {
        when(carRepository.findById("car-1")).thenReturn(car);

        Car result = carService.findById("car-1");

        assertSame(car, result);
        verify(carRepository).findById("car-1");
    }

    @Test
    void testUpdate() {
        carService.update("car-1", car);
        verify(carRepository).update("car-1", car);
    }

    @Test
    void testDeleteById() {
        carService.deleteCarById("car-1");
        verify(carRepository).delete("car-1");
    }
}
