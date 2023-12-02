package gcs.moi.controller;

import gcs.moi.dto.Response;
import gcs.moi.dto.request.MoneyRequest;
import gcs.moi.dto.response.MoneyResponse;
import gcs.moi.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/money")
@RequiredArgsConstructor
public class MoneyController {

    private final MoneyService moneyService;

    @PostMapping("")
    public Response<MoneyResponse> save(@RequestBody MoneyRequest moneyRequest) {
        return Response.success(moneyService.save(moneyRequest));
    }
}
