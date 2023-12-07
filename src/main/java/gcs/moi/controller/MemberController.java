package gcs.moi.controller;

import gcs.moi.dto.Response;
import gcs.moi.dto.request.MemberRequest;
import gcs.moi.dto.response.MemberResponse;
import gcs.moi.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public Response<MemberResponse> join(@RequestBody MemberRequest memberRequest) {
        return Response.success(memberService.save(memberRequest));
    }

    @PostMapping("/login")
    public Response<MemberResponse> login(@RequestBody MemberRequest memberRequest, HttpServletRequest request) {
        return Response.success(memberService.login(memberRequest, request));
    }
}
