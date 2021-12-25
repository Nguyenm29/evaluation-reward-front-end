package com.vis.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeKeeping {
    private String employeeId;
    private String name;
    private float[] workDays;
    private int overTime;
}
