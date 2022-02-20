package com.example.final_finaaaal;

public class Room implements java.io.Serializable{
    int room_id;
    boolean status;
    String descreption;
    int floor;
    String room_photo;
    String room_type;
    double price;


    public Room() {
    }

    public Room(int room_id, boolean status, String descreption, int floor, String room_photo, String room_type,double price) {
        this.room_id = room_id;
        this.status = status;
        this.descreption = descreption;
        this.floor = floor;
        this.room_photo = room_photo;
        this.room_type = room_type;
        this.price = price;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoom_photo() {
        return room_photo;
    }

    public void setRoom_photo(String room_photo) {
        this.room_photo = room_photo;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", status=" + status +
                ", descreption='" + descreption + '\'' +
                ", floor=" + floor +
                ", room_photo='" + room_photo + '\'' +
                ", room_type='" + room_type + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
