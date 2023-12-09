package gcs.moi.repository;

import gcs.moi.domain.Money;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MoneyRepository extends JpaRepository<Money, Long> {

    Optional<Money> findByRoomIdAndMemberId(Long roomId, Long memberId);

    @Modifying
    @Transactional
    @Query("UPDATE Money m SET m.amount = m.amount + :addAmount WHERE m.id = :id")
    int updateMoneyAmount(Long id, Long addAmount);
}
