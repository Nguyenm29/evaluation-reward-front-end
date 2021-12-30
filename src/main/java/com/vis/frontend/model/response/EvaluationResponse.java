package com.vis.frontend.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluationResponse {
    private float dayWork;
    private float dayOff;
    private float lateTime;
    private String month;
    private float overTime;
    private float totalPoint;
}
