package com.vis.frontend.service;

import com.vis.frontend.model.response.RewardPointResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface PointService {

    public List<RewardPointResponse> calPoint(HttpServletRequest httpRequest, MultipartFile file) throws IOException;

    public boolean save(HttpServletRequest request);

    public float getPointReward(HttpServletRequest httpServletRequest, Long employeeId);

}
