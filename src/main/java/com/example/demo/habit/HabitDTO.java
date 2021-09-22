package com.example.demo.habit;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HabitDTO {
    private String name;
    private String description;
    private LocalDate initialDate;

    @ApiModelProperty(dataType = "[Ljava.lang.String;")
    private List<LocalDate> habitDates = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public List<LocalDate> getHabitDates() {
        return habitDates;
    }

    public void setHabitDates(List<LocalDate> habitDates) {
        this.habitDates = habitDates;
    }
}
