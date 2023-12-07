package gcs.moi.controller;

import gcs.moi.dto.Response;
import gcs.moi.dto.request.ItemSaveRequest;
import gcs.moi.dto.response.ItemSaveResponse;
import gcs.moi.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("")
    public Response<ItemSaveResponse> save(@RequestBody ItemSaveRequest itemSaveRequest) {
        return Response.success(itemService.save(itemSaveRequest));
    }
}
