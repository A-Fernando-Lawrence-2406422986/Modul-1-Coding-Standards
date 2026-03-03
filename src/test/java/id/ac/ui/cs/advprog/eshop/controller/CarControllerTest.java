package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarServiceImpl carservice;

    @Mock
    private Model model;

    @InjectMocks
    private CarController carController;

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("car-1");
        car.setCarName("Sedan");
        car.setCarColor("Black");
        car.setCarQuantity(1);
    }

    @Test
    void testCreateCarPage() {
        String viewName = carController.createCarPage(model);

        assertEquals("createCar", viewName);
        verify(model).addAttribute(org.mockito.ArgumentMatchers.eq("car"), any(Car.class));
    }

    @Test
    void testCreateCarPost() {
        String viewName = carController.createCarPost(car, model);

        assertEquals("redirect:listCar", viewName);
        verify(carservice).create(car);
    }

    @Test
    void testCarListPage() {
        when(carservice.findAll()).thenReturn(List.of(car));

        String viewName = carController.carListPage(model);

        assertEquals("carList", viewName);
        verify(model).addAttribute("cars", List.of(car));
    }

    @Test
    void testEditCarPage() {
        when(carservice.findById("car-1")).thenReturn(car);

        String viewName = carController.editCarPage("car-1", model);

        assertEquals("editCar", viewName);
        verify(model).addAttribute("car", car);
    }

    @Test
    void testEditCarPost() {
        String viewName = carController.editCarPost(car, model);

        assertEquals("redirect:listCar", viewName);
        verify(carservice).update("car-1", car);
    }

    @Test
    void testDeleteCar() {
        String viewName = carController.deleteCar("car-1");

        assertEquals("redirect:listCar", viewName);
        verify(carservice).deleteCarById("car-1");
    }
}
