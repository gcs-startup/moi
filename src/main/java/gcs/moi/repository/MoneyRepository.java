package gcs.moi.repository;

import gcs.moi.domain.Member;
import gcs.moi.domain.Money;
import gcs.moi.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoneyRepository extends JpaRepository<Money, Long> {

    Optional<Money> findByRoomAndMember(Room room, Member member);
}
