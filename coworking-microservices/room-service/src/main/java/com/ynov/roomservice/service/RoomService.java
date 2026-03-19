package com.ynov.roomservice.service;

import com.ynov.roomservice.exception.ResourceNotFoundException;
import com.ynov.roomservice.kafka.RoomKafkaProducer;
import com.ynov.roomservice.model.Room;
import com.ynov.roomservice.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomKafkaProducer roomKafkaProducer;

    public RoomService(RoomRepository roomRepository, RoomKafkaProducer roomKafkaProducer) {
        this.roomRepository = roomRepository;
        this.roomKafkaProducer = roomKafkaProducer;
    }

    @Transactional(readOnly = true)
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));
    }

    public Room createRoom(Room room) {
        room.setAvailable(true);
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room roomDetails) {
        Room room = getRoomById(id);
        room.setName(roomDetails.getName());
        room.setCity(roomDetails.getCity());
        room.setCapacity(roomDetails.getCapacity());
        room.setType(roomDetails.getType());
        room.setHourlyRate(roomDetails.getHourlyRate());
        return roomRepository.save(room);
    }

    public void deleteRoom(Long id) {
        Room room = getRoomById(id);
        roomRepository.delete(room);
        roomKafkaProducer.sendRoomDeletedEvent(id.toString());
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return roomRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailableTrue();
    }

    @Transactional(readOnly = true)
    public boolean isAvailable(Long id) {
        Room room = getRoomById(id);
        return room.isAvailable();
    }

    public void updateAvailability(Long id, boolean available) {
        Room room = getRoomById(id);
        room.setAvailable(available);
        roomRepository.save(room);
    }
}
