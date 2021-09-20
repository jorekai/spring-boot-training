package com.example.demo.habit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(path = "api/v1/habit")
public class HabitController {

    private final HabitService habitService;

    @Autowired
    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping("")
    public List<Habit> getHabits() {
        return habitService.getHabits();
    }

    @PostMapping("")
    public void postHabit(@RequestBody HabitDTO habit) {
        habitService.addNewHabit(habit);
    }

    @GetMapping(path = "{habitId}")
    public Optional<Habit> getHabit(@PathVariable("habitId") Long id) {
        return habitService.getHabitById(id);
    }

    @DeleteMapping(path = "{habitId}")
    public void deleteHabit(@PathVariable("habitId") Long id) {
        habitService.deleteHabitById(id);
    }

    @PutMapping(path = "{habitId}")
    public void updateHabit(@PathVariable("habitId") Long id, @RequestBody HabitDTO habit) {
        habitService.updateHabitById(id, habit);
    }
}

