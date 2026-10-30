package com.appointment.project.appointmentbackend.config;

import com.appointment.project.appointmentbackend.entity.ProviderCategory;
import com.appointment.project.appointmentbackend.repository.ProviderCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProviderCategoryRepository repo;

    public DataLoader(ProviderCategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {

        if (repo.count() == 0) {

            List<String> names = List.of(
                "Doctor",
                "Engineer",
                "Software Engineer",
                "Mechanic",
                "Electrician",
                "Plumber",
                "Barber"
            );

            names.forEach(n -> {
                ProviderCategory c = new ProviderCategory();
                c.setCategoryName(n);
                repo.save(c);
            });

            System.out.println("✔ Categories seeded");
        } else {
            System.out.println("✔ Categories already exist");
        }
    }
}
