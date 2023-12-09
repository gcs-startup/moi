package gcs.moi.repository;

import gcs.moi.domain.Item;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByRoomId(Long roomId);

    @Modifying
    @Transactional
    @Query("UPDATE Item i SET i.amount = i.amount + :amount WHERE i.id = :id")
    int updateItemAmount(Long id, Long amount);
}
