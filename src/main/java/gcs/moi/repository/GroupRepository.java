package gcs.moi.repository;

import gcs.moi.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Room, Long> {
}
