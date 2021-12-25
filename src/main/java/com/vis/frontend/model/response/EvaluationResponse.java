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
    private int month;
    private float overTime;
    private float totalPoint;
}
