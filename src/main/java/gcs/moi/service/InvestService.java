package gcs.moi.service;

import gcs.moi.config.exception.ErrorCode;
import gcs.moi.config.exception.MoiApplicationException;
import gcs.moi.domain.*;
import gcs.moi.dto.request.InvestRequest;
import gcs.moi.dto.response.InvestResponse;
import gcs.moi.repository.InvestRepository;
import gcs.moi.repository.ItemRepository;
import gcs.moi.repository.MemberRepository;
import gcs.moi.repository.MoneyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestService {

    private final InvestRepository investRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final MoneyRepository moneyRepository;

    @Transactional
    public InvestResponse invest(InvestRequest investRequest) {
        Item item = getItemByIdOrElseThrow(investRequest.getItemId());
        Member member = getMemberByIdOrElseThrow(investRequest.getMemberId());
        Money money = getMoneyByRoomAndMemberOrElseThrow(item.getRoom(), member);

        Invest invest = investRepository.save(Invest.of(item, member, investRequest.getAmount()));
        item.change(investRequest.getAmount());
        money.change(-investRequest.getAmount());

        return InvestResponse.from(invest);
    }

    private Item getItemByIdOrElseThrow(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new MoiApplicationException(ErrorCode.ITEM_NOT_FOUND));
    }

    private Member getMemberByIdOrElseThrow(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MoiApplicationException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private Money getMoneyByRoomAndMemberOrElseThrow(Room room, Member member) {
        return moneyRepository.findByRoomAndMember(room, member)
                .orElseThrow(() -> new MoiApplicationException(ErrorCode.MONEY_NOT_FOUND));
    }
}
