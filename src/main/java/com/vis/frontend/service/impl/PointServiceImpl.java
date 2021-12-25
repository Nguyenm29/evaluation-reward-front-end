package com.vis.frontend.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.vis.frontend.model.TimeKeeping;
import com.vis.frontend.model.response.RewardPointResponse;
import com.vis.frontend.service.AbstractService;
import com.vis.frontend.service.ApiExchangeService;
import com.vis.frontend.service.PointService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.channels.FileLock;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class PointServiceImpl extends AbstractService implements PointService {

    @Value("${back.api.url}")
    private String backApi;

    @Override
    public List<RewardPointResponse> calPoint(HttpServletRequest httpRequest, MultipartFile file) throws IOException {

        String url = apiExchangeService.createURL(backApi, "/admin/point/cal");
        ResponseEntity<List> response = apiExchangeService.postFile(httpRequest, url, file.getResource(), List.class);
        //ResponseEntity<Boolean> response = apiExchangeService.
        return (List<RewardPointResponse>) response.getBody();
    }

    @Override
    public boolean save(HttpServletRequest request) {
        String url = apiExchangeService.createURL(backApi, "/admin/point/save");

        List listCalEmployee = (List) request.getSession().getAttribute("listCalEmployee");
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("listCalEmployee", listCalEmployee);
        ResponseEntity<Boolean> response = apiExchangeService.post(request, url, listCalEmployee, boolean.class, body);
        return response.getBody();
    }

    @Override
    public float getPointReward(HttpServletRequest request, Long employeeId) {
        String url = apiExchangeService.createURL(backApi + "/point/reward?employeeId="+employeeId);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("employeeId", employeeId);
        ResponseEntity<Float> response = (ResponseEntity<Float>) apiExchangeService.get(request, url, Float.class, MediaType.APPLICATION_JSON, body);
        return Float.valueOf(String.valueOf(response.getBody()));
    }

}
