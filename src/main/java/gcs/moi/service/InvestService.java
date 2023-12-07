package gcs.moi.service;

import gcs.moi.auth.web.AuthenticatedMember;
import gcs.moi.config.exception.ErrorCode;
import gcs.moi.config.exception.MoiApplicationException;
import gcs.moi.domain.*;
import gcs.moi.dto.invest.MemberInvest;
import gcs.moi.dto.request.InvestRequest;
import gcs.moi.dto.response.InvestResponse;
import gcs.moi.repository.InvestRepository;
import gcs.moi.repository.ItemRepository;
import gcs.moi.repository.MoneyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestService {

    private final ItemRepository itemRepository;
    private final InvestRepository investRepository;
    private final MoneyRepository moneyRepository;
    private final AuthenticatedMember authenticatedMember;

    public List<MemberInvest> getMemberInvestsByRoomId(Long roomId) {
        Member member = authenticatedMember.orElseThrow();
        return investRepository.sumInvestByMemberAndRoomGroupedByItem(member.getId(), roomId);
    }

    @Transactional
    public InvestResponse invest(InvestRequest investRequest) {
        Member member = authenticatedMember.orElseThrow();
        Item item = getItemByIdOrElseThrow(investRequest.getItemId());
        Money money = getMoneyByRoomAndMemberOrElseThrow(item.getRoom(), member);

        Invest invest = investRepository.save(Invest.of(item, member, investRequest.getAmount()));
        item.change(investRequest.getAmount());
        money.change(-investRequest.getAmount());

        return InvestResponse.from(invest);
    }

    private Money getMoneyByRoomAndMemberOrElseThrow(Room room, Member member) {
        return moneyRepository.findByRoomIdAndMemberId(room.getId(), member.getId())
                .orElseThrow(() -> new MoiApplicationException(ErrorCode.MONEY_NOT_FOUND));
    }

    public Item getItemByIdOrElseThrow(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new MoiApplicationException(ErrorCode.ITEM_NOT_FOUND));
    }
}
