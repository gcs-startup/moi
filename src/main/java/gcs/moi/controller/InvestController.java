package gcs.moi.controller;

import gcs.moi.dto.Response;
import gcs.moi.dto.request.InvestRequest;
import gcs.moi.dto.response.InvestResponse;
import gcs.moi.service.InvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/invest")
@RequiredArgsConstructor
public class InvestController {

    private final InvestService investService;

    @PostMapping("")
    public Response<InvestResponse> invest(@RequestBody InvestRequest investRequest) {
        return Response.success(investService.invest(investRequest));
    }
}
