package gcs.moi.repository;

import gcs.moi.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByRoomId(Long roomId);
}
