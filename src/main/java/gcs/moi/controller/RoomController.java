package gcs.moi.controller;

import gcs.moi.dto.Response;
import gcs.moi.dto.request.RoomRequest;
import gcs.moi.dto.response.RoomResponse;
import gcs.moi.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("")
    public Response<RoomResponse> save(@RequestBody RoomRequest roomRequest) {
        return Response.success(roomService.save(roomRequest));
    }
}
