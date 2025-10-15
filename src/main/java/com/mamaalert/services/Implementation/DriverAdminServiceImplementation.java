package com.mamaalert.services.Implementation;

import com.mamaalert.data.model.Driver;
import com.mamaalert.data.repository.DriverRepo;
import com.mamaalert.dtos.requests.RegisterDriverRequest;
import com.mamaalert.dtos.responses.RegisterDriverResponse;
import com.mamaalert.services.DriverAdminService;
import com.mamaalert.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DriverAdminServiceImplementation implements DriverAdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DriverRepo driverRepo;

    @Override
    public RegisterDriverResponse registerDriver(RegisterDriverRequest request) {
        Driver driver = Mapper.mapRequestToDriver(request, passwordEncoder);
        Driver savedDriver = driverRepo.save(driver);

        RegisterDriverResponse response = new RegisterDriverResponse();
        response.setId(savedDriver.getId());
        response.setMessage("Driver has been registered successfully");
        return response;
    }
}
