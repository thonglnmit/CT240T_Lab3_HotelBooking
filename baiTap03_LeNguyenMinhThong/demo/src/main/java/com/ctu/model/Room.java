package com.ctu.model;

public class Room {

    private String roomId;
    private double pricePerNight;
    private boolean isAvailable;

    public Room(String roomId, double pricePerNight, boolean isAvailable) {

        this.roomId = roomId;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;

    }

    public String getRoomId() { return roomId; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

}

public class BookingReceipt {

    private String bookingId;
    private String roomId;
    private double totalAmount;
    public BookingReceipt(String bookingId, String roomId, double totalAmount) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.totalAmount = totalAmount;
    }

    public String getBookingId() { return bookingId; }
    public String getRoomId() { return roomId; }
    public double getTotalAmount() { return totalAmount; }
}