package gcs.moi.controller;

import gcs.moi.dto.Response;
import gcs.moi.dto.request.MoneyRequest;
import gcs.moi.dto.request.MoneySaveRequest;
import gcs.moi.dto.response.MoneyResponse;
import gcs.moi.dto.response.MoneySaveResponse;
import gcs.moi.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/money")
@RequiredArgsConstructor
public class MoneyController {

    private final MoneyService moneyService;

    @GetMapping("")
    public Response<MoneyResponse> get(@RequestParam("roomId") Long roomId) {
        return Response.success(moneyService.findByMemberId(roomId));
    }

    @PostMapping("")
    public Response<MoneySaveResponse> save(@RequestBody MoneySaveRequest moneySaveRequest) {
        return Response.success(moneyService.save(moneySaveRequest));
    }
}
