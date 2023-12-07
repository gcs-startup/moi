package gcs.moi.repository;

import gcs.moi.domain.Invest;
import gcs.moi.dto.invest.MemberInvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvestRepository extends JpaRepository<Invest, Long> {

    @Query("SELECT new gcs.moi.dto.invest.MemberInvest(:memberId, :roomId, i.item.id, SUM(i.amount)) FROM Invest i WHERE i.investor.id = :memberId AND i.item.room.id = :roomId GROUP BY i.item.id")
    List<MemberInvest> sumInvestByMemberAndRoomGroupedByItem(@Param("memberId") Long memberId, @Param("roomId") Long roomId);
}
