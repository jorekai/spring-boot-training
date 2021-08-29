package com.example.demo.habit;

import com.example.demo.utils.OptionalPropertyCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HabitService {

    private final HabitRepository habitRepository;

    @Autowired
    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public List<Habit> getHabits() {
        return habitRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public void addNewHabit(@org.jetbrains.annotations.NotNull HabitDTO habit) {
        Optional<Habit> habitOptional = habitRepository.findHabitByName(habit.getName());

        if (habitOptional.isPresent()) {
            throw new IllegalStateException("Habit exists already!");
        }
        Habit targetHabit = new Habit();
        OptionalPropertyCopy.copyPropertiesOptional(habit, targetHabit);
        habitRepository.save(targetHabit);
    }

    public void deleteHabitById(Long id) {
        if (!habitRepository.existsById(id)) {
            throw new IllegalStateException(String.format("Habit with id %d does not exist!", id));
        }
        habitRepository.deleteById(id);
    }

    @Transactional
    public void updateHabitById(Long id, HabitDTO habit) {
        if (!habitRepository.existsById(id)) {
            throw new IllegalStateException(String.format("Habit with id %d does not exist!", id));
        }
        Habit sourceHabit = habitRepository.getById(id);
        OptionalPropertyCopy.copyPropertiesOptional(habit, sourceHabit);
        habitRepository.save(sourceHabit);
    }
}
