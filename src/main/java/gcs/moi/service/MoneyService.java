package gcs.moi.service;

import gcs.moi.auth.web.AuthenticatedMember;
import gcs.moi.config.exception.ErrorCode;
import gcs.moi.config.exception.MoiApplicationException;
import gcs.moi.domain.Member;
import gcs.moi.domain.Money;
import gcs.moi.dto.request.MoneySaveRequest;
import gcs.moi.dto.response.MoneyResponse;
import gcs.moi.dto.response.MoneySaveResponse;
import gcs.moi.repository.MoneyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoneyService {

    private final RoomService roomService;
    private final MemberService memberService;
    private final MoneyRepository moneyRepository;
    private final AuthenticatedMember authenticatedMember;

    public MoneyResponse findByMemberId(Long roomId) {
        Member member = authenticatedMember.orElseThrow();
        Money money = moneyRepository.findByRoomIdAndMemberId(roomId, member.getId())
                .orElseThrow(() -> new MoiApplicationException(ErrorCode.MONEY_NOT_FOUND));
        return MoneyResponse.from(money);
    }

    @Transactional
    public MoneySaveResponse save(MoneySaveRequest moneySaveRequest) {
        Money money = moneyRepository.save(Money.of(
                roomService.findByIdOrElseThrow(moneySaveRequest.getRoomId()),
                memberService.findByIdOrElseThrow(moneySaveRequest.getMemberId()),
                moneySaveRequest.getAmount()));
        return MoneySaveResponse.from(money);
    }
}
