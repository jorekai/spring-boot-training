package com.example.demo.habit;

import com.example.demo.utils.OptionalPropertyCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
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
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Habit exists already.");
        }
        Habit targetHabit = new Habit();
        OptionalPropertyCopy.copyPropertiesOptional(habit, targetHabit);
        habitRepository.save(targetHabit);
    }

    public Optional<Habit> getHabitById(Long id) {
        throwExceptionIfNotFound(id);
        return habitRepository.findById(id);
    }

    public void deleteHabitById(Long id) {
        throwExceptionIfNotFound(id);
        habitRepository.deleteById(id);
    }

    public void addNewHabitDate(Long id, Optional<LocalDate> date) {
        throwExceptionIfNotFound(id);
        Habit habit = habitRepository.getById(id);
        List<LocalDate> currentDates = habit.getHabitDates();
        LocalDate startDate = habit.getInitialDate();
        LocalDate newDate = date.orElse(LocalDate.now());

        if (currentDates.contains(newDate) || newDate.isBefore(startDate)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, String.format("Habit with id %d cannot accept the input Date %s!", id, date));
        }
        currentDates.add(newDate);
        habitRepository.save(habit);
    }

    @Transactional
    public void updateHabitById(Long id, HabitDTO habit) {
        throwExceptionIfNotFound(id);
        Habit sourceHabit = habitRepository.getById(id);
        OptionalPropertyCopy.copyPropertiesOptional(habit, sourceHabit);
        habitRepository.save(sourceHabit);
    }

    private void throwExceptionIfNotFound(Long id) {
        if (!habitRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Habit with id %d does not exist!", id));
        }
    }
}
