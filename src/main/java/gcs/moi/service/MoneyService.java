package gcs.moi.service;

import gcs.moi.domain.Money;
import gcs.moi.dto.request.MoneyRequest;
import gcs.moi.dto.response.MoneyResponse;
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

    @Transactional
    public MoneyResponse save(MoneyRequest moneyRequest) {
        Money money = moneyRepository.save(Money.of(
                roomService.findByIdOrElseThrow(moneyRequest.getRoomId()),
                memberService.findByIdOrElseThrow(moneyRequest.getMemberId()),
                moneyRequest.getAmount()));
        return MoneyResponse.from(money);
    }
}
