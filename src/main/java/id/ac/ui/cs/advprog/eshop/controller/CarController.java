package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarCreator;
import id.ac.ui.cs.advprog.eshop.service.CarDeleter;
import id.ac.ui.cs.advprog.eshop.service.CarReader;
import id.ac.ui.cs.advprog.eshop.service.CarUpdater;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/car")
public class CarController {
    private final CarCreator carCreator;
    private final CarReader carReader;
    private final CarUpdater carUpdater;
    private final CarDeleter carDeleter;

    public CarController(
            CarCreator carCreator,
            CarReader carReader,
            CarUpdater carUpdater,
            CarDeleter carDeleter
    ) {
        this.carCreator = carCreator;
        this.carReader = carReader;
        this.carUpdater = carUpdater;
        this.carDeleter = carDeleter;
    }

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        model.addAttribute("car", new Car());
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car) {
        carCreator.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        model.addAttribute("cars", carReader.findAll());
        return "carList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        model.addAttribute("car", carReader.findById(carId));
        return "editCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car) {
        carUpdater.update(car.getCarId(), car);
        return "redirect:listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") String carId) {
        carDeleter.deleteCarById(carId);
        return "redirect:listCar";
    }
}
