package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new InMemoryCarRepository();
    }

    @Test
    void testCreateWithProvidedId() {
        Car car = new Car();
        car.setCarId("car-1");
        car.setCarName("Sedan");
        car.setCarColor("Black");
        car.setCarQuantity(3);

        Car createdCar = carRepository.create(car);

        assertEquals("car-1", createdCar.getCarId());
        assertEquals("Sedan", createdCar.getCarName());
        assertEquals("Black", createdCar.getCarColor());
        assertEquals(3, createdCar.getCarQuantity());
    }

    @Test
    void testCreateWithNullIdGeneratesUuid() {
        Car car = new Car();
        car.setCarName("SUV");
        car.setCarColor("White");
        car.setCarQuantity(2);

        Car createdCar = carRepository.create(car);

        assertNotNull(createdCar.getCarId());
    }

    @Test
    void testFindAllAndFindById() {
        Car car = new Car();
        car.setCarId("car-2");
        car.setCarName("Hatchback");
        car.setCarColor("Blue");
        car.setCarQuantity(1);
        carRepository.create(car);

        Iterator<Car> cars = carRepository.findAll();
        assertTrue(cars.hasNext());
        assertEquals("car-2", cars.next().getCarId());

        Car foundCar = carRepository.findById("car-2");
        assertNotNull(foundCar);
        assertEquals("Hatchback", foundCar.getCarName());
    }

    @Test
    void testFindByIdNotFound() {
        Car foundCar = carRepository.findById("missing-id");
        assertNull(foundCar);
    }

    @Test
    void testFindByIdNotFoundInNonEmptyRepository() {
        Car car = new Car();
        car.setCarId("car-10");
        car.setCarName("Roadster");
        car.setCarColor("Yellow");
        car.setCarQuantity(2);
        carRepository.create(car);

        Car foundCar = carRepository.findById("missing-id");
        assertNull(foundCar);
    }

    @Test
    void testUpdateFoundAndNotFound() {
        Car car = new Car();
        car.setCarId("car-3");
        car.setCarName("Coupe");
        car.setCarColor("Red");
        car.setCarQuantity(4);
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Coupe Pro");
        updatedCar.setCarColor("Silver");
        updatedCar.setCarQuantity(5);

        Car result = carRepository.update("car-3", updatedCar);
        assertNotNull(result);
        assertEquals("Coupe Pro", result.getCarName());
        assertEquals("Silver", result.getCarColor());
        assertEquals(5, result.getCarQuantity());

        Car missingUpdate = carRepository.update("does-not-exist", updatedCar);
        assertNull(missingUpdate);
    }

    @Test
    void testDelete() {
        Car car = new Car();
        car.setCarId("car-4");
        car.setCarName("Wagon");
        car.setCarColor("Green");
        car.setCarQuantity(6);
        carRepository.create(car);

        carRepository.delete("car-4");

        assertNull(carRepository.findById("car-4"));
        assertFalse(carRepository.findAll().hasNext());
    }
}
