
package com.ctu.service;

import com.ctu.model.BookingReceipt;
import com.ctu.model.Room;
import com.ctu.repository.RoomRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private PaymentGateway paymentGateway;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void testBookRoom_Success() {

        // Arrange: Chuan bi du lieu
        Room room = new Room("A101", 500000, true);

        when(roomRepository.findById("A101"))
                .thenReturn(Optional.of(room));

        when(paymentGateway.charge("4111", 1000000))
                .thenReturn(true);

        // Act: Thuc hien dat phong
        BookingReceipt receipt =
                bookingService.bookRoom("A101", 2, "4111");

        // Assert: Kiem tra ket qua
        assertNotNull(receipt);

        assertNotNull(receipt.getBookingId());
        assertEquals("A101", receipt.getRoomId());
        assertEquals(1000000.0, receipt.getTotalAmount(), 0.001);

        assertFalse(room.isAvailable());

        // Verify: Kiem tra so lan goi phuong thuc
        verify(roomRepository, times(1)).findById("A101");
        verify(paymentGateway, times(1))
                .charge("4111", 1000000.0);
        verify(roomRepository, times(1)).save(room);
    }
}
