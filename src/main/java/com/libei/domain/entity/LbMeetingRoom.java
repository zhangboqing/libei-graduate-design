package com.libei.domain.entity;

public class LbMeetingRoom {
    private Integer roomId;

    private Integer roomNo;

    private Integer roomCanInNumber;

    private String roomFacility;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getRoomCanInNumber() {
        return roomCanInNumber;
    }

    public void setRoomCanInNumber(Integer roomCanInNumber) {
        this.roomCanInNumber = roomCanInNumber;
    }

    public String getRoomFacility() {
        return roomFacility;
    }

    public void setRoomFacility(String roomFacility) {
        this.roomFacility = roomFacility;
    }
}