package gcs.moi.controller;

import gcs.moi.dto.Response;
import gcs.moi.dto.request.MemberRequest;
import gcs.moi.dto.response.MemberResponse;
import gcs.moi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    public Response<MemberResponse> join(@RequestBody MemberRequest memberRequest) {
        return Response.success(memberService.save(memberRequest));
    }
}
