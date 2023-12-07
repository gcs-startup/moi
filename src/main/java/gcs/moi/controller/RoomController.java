package gcs.moi.controller;

import gcs.moi.dto.Response;
import gcs.moi.dto.request.RoomRequest;
import gcs.moi.dto.response.ItemResponse;
import gcs.moi.dto.response.RoomResponse;
import gcs.moi.service.ItemService;
import gcs.moi.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final ItemService itemService;

    @GetMapping("/{roomId}/items")
    public Response<List<ItemResponse>> getItemsByRoomId(@PathVariable Long roomId) {
        return Response.success(itemService.getItemsByRoomId(roomId));
    }

    @PostMapping("")
    public Response<RoomResponse> save(@RequestBody RoomRequest roomRequest) {
        return Response.success(roomService.save(roomRequest));
    }
}
