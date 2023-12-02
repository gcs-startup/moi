package gcs.moi.controller;

import gcs.moi.dto.Response;
import gcs.moi.dto.request.ItemRequest;
import gcs.moi.dto.response.ItemResponse;
import gcs.moi.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("")
    public Response<ItemResponse> save(@RequestBody ItemRequest itemRequest) {
        return Response.success(itemService.save(itemRequest));
    }
}
