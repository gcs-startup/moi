package gcs.moi.service;

import gcs.moi.domain.Room;
import gcs.moi.dto.request.RoomRequest;
import gcs.moi.dto.response.RoomResponse;
import gcs.moi.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional
    public RoomResponse save(RoomRequest roomRequest) {
        Room room = roomRepository.save(Room.of(roomRequest.getTitle()));
        return RoomResponse.from(room);
    }
}
