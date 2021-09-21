package com.example.demo.habit;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ExampleProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping(path = "{habitId}")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "date",
                    dataTypeClass = LocalDate.class,
                    examples = @io.swagger.annotations.Example(
                            value = {
                                    @ExampleProperty(value = "2021-01-01", mediaType = "application/json")
                            }))
    })
    public void addNewHabitDate(@PathVariable("habitId") Long id, @RequestBody Optional<LocalDate> date) {
        habitService.addNewHabitDate(id, date);
    }
}

