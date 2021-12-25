package com.vis.frontend.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RewardPointResponse {
    private String employeeID;
    private String name;
    private float dayWork;
    private float dayLeft;
    private float workDayRate;
    private float overTimeRate;
    private float overTime;
    private float totalPoint;
    private int timeLate;
    private float timeLateRate;
}
