package gcs.moi.service;

import gcs.moi.auth.web.AuthenticatedMember;
import gcs.moi.config.exception.ErrorCode;
import gcs.moi.config.exception.MoiApplicationException;
import gcs.moi.domain.Item;
import gcs.moi.dto.request.ItemRequest;
import gcs.moi.dto.response.ItemResponse;
import gcs.moi.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final RoomService roomService;
    private final ItemRepository itemRepository;
    private final AuthenticatedMember authenticatedMember;

    @Transactional
    public ItemResponse save(ItemRequest itemRequest) {
        Item item = itemRepository.save(Item.of(
                itemRequest.getTitle(),
                itemRequest.getDescription(),
                0L,
                false,
                roomService.findByIdOrElseThrow(itemRequest.getRoomId()),
                authenticatedMember.get()));
        return ItemResponse.from(item);
    }

    public Item findByIdOrElseThrow(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new MoiApplicationException(ErrorCode.ITEM_NOT_FOUND));
    }
}
