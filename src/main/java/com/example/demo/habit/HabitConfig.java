package com.example.demo.habit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class HabitConfig {

    @Bean
    CommandLineRunner commandLineRunner(HabitRepository habitRepository) {
        return args -> {
            Habit stretch = new Habit(
                    "Stretching",
                    "Stretching Routine.",
                    LocalDate.of(2021, 8, 1)
            );
            Habit workout = new Habit(
                    "Workout",
                    "Workout Routine.",
                    LocalDate.of(2021, 8, 22)
            );

            habitRepository.saveAll(List.of(stretch, workout));
        };
    }
}
