package gcs.moi.service;

import gcs.moi.auth.web.AuthenticatedMember;
import gcs.moi.config.exception.ErrorCode;
import gcs.moi.config.exception.MoiApplicationException;
import gcs.moi.domain.Item;
import gcs.moi.dto.invest.MemberInvest;
import gcs.moi.dto.request.ItemSaveRequest;
import gcs.moi.dto.response.ItemResponse;
import gcs.moi.dto.response.ItemSaveResponse;
import gcs.moi.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final RoomService roomService;
    private final ItemRepository itemRepository;
    private final InvestService investService;
    private final AuthenticatedMember authenticatedMember;

    public List<ItemResponse> getItemsByRoomId(Long roomId) {
        List<ItemResponse> itemResponses = itemRepository.findAllByRoomId(roomId)
                .stream()
                .map(ItemResponse::from)
                .toList();

        List<MemberInvest> memberInvestsByRoomId = investService.getMemberInvestsByRoomId(roomId);

        return itemResponses.stream()
                .map(ir -> {
                    Long investAmount = memberInvestsByRoomId.stream()
                            .filter(mi -> mi.getItemId().equals(ir.getId()))
                            .findFirst()
                            .map(MemberInvest::getInvestAmount)
                            .orElse(0L);
                    return ItemResponse.fromWithMemberAmount(ir, investAmount);
                })
                .toList();
    }

    @Transactional
    public ItemSaveResponse save(ItemSaveRequest itemSaveRequest) {
        Item item = itemRepository.save(Item.of(
                itemSaveRequest.getTitle(),
                itemSaveRequest.getDescription(),
                0L,
                false,
                roomService.findByIdOrElseThrow(itemSaveRequest.getRoomId()),
                authenticatedMember.orElseThrow()));
        return ItemSaveResponse.from(item);
    }

    public Item findByIdOrElseThrow(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new MoiApplicationException(ErrorCode.ITEM_NOT_FOUND));
    }
}
