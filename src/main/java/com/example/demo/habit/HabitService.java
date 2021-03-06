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

        habitOptional.ifPresentOrElse(h -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT, "Habit exists already.");
                },
                () -> {
                });
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
        Habit habit = tryToGetHabitById(id);
        List<LocalDate> currentDates = habit.getHabitDates();
        LocalDate startDate = habit.getInitialDate();
        LocalDate newDate = date.orElse(LocalDate.now());
        Boolean inputDateIsWrong = currentDates.contains(newDate) || newDate.isBefore(startDate);

        if (Boolean.TRUE.equals(inputDateIsWrong)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, String.format("Habit with id %d cannot accept the input Date %s!", id, date));
        }
        currentDates.add(newDate);
        habitRepository.save(habit);
    }

    public void removeDateFromList(Long id, LocalDate date) {
        Habit habit = tryToGetHabitById(id);
        List<LocalDate> currentDates = habit.getHabitDates();
        LocalDate startDate = habit.getInitialDate();
        Boolean inputDateIsWrong = !currentDates.contains(date) || date.isBefore(startDate);

        if (Boolean.TRUE.equals(inputDateIsWrong)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, String.format("Habit with id %d cannot accept the input Date %s!", id, date));
        }
        currentDates.remove(date);
        habitRepository.save(habit);
    }

    @Transactional
    public void updateHabitById(Long id, HabitDTO habit) {
        Habit sourceHabit = tryToGetHabitById(id);
        OptionalPropertyCopy.copyPropertiesOptional(habit, sourceHabit);
        habitRepository.save(sourceHabit);
    }

    private void throwExceptionIfNotFound(Long id) {
        if (!habitRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Habit with id %d does not exist!", id));
        }
    }

    private Habit tryToGetHabitById(Long id) {
        throwExceptionIfNotFound(id);
        return habitRepository.getById(id);
    }
}
