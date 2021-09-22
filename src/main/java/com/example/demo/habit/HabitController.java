package com.example.demo.habit;

import com.example.demo.swaggerui.SwaggerConfig;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Api(tags = {SwaggerConfig.HABIT_CTRL})
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
                    dataType = "java.time.LocalDate",
                    dataTypeClass = String.class,
                    paramType = "body",
                    examples = @Example(
                            value = {
                                    @ExampleProperty(value = "2021-11-11", mediaType = "application/json")
                            }))
    })
    public void addNewHabitDate(@PathVariable("habitId") Long id, @RequestBody(required = false) Optional<LocalDate> date) {
        habitService.addNewHabitDate(id, date);
    }
}

