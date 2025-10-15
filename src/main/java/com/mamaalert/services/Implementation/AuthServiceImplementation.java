package com.mamaalert.services.Implementation;

import com.mamaalert.data.model.User;
import com.mamaalert.data.repository.*;
import com.mamaalert.dtos.requests.LoginRequest;
import com.mamaalert.dtos.responses.LoginResponse;
import com.mamaalert.security.JwtUtil;
import com.mamaalert.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImplementation implements AuthService {

    @Autowired
    private SuperAdminRepo superAdminRepository;

    @Autowired
    private HospitalRepo hospitalRepository;

    @Autowired
    private PatientRepo patientRepository;

    @Autowired
    private DriverAdminRepo driverAdminRepository;

    @Autowired
    private DriverRepo driverRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = findUserByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return new LoginResponse(
                token,
                user.getEmail(),
                user.getRole().name(),
                "Login successful"
        );
    }

    // Multi-repo lookup
    @Override
    public User findUserByEmail(String email) {
        List<UserRepository<? extends User>> repos = List.of(
                superAdminRepository,
                hospitalRepository,
                patientRepository,
                driverAdminRepository,
                driverRepository
        );

        return repos.stream()
                .map(repo -> repo.findByEmail(email)) // Optional<? extends User>
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
