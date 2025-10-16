package com.mamaalert.services.Implementation;


import com.mamaalert.data.model.Driver;
import com.mamaalert.data.repository.DriverRepo;
import com.mamaalert.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverServiceImplementation implements DriverService {

    private final DriverRepo driverRepository;

    @Override
    public Driver updateLocation(String driverEmail, double latitude, double longitude) {
        Driver driver = driverRepository.findByEmail(driverEmail)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setLatitude(latitude);
        driver.setLongitude(longitude);

        return driverRepository.save(driver);
    }
}

