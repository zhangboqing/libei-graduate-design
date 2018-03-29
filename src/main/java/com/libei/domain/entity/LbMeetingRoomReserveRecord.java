package com.libei.domain.entity;

public class LbMeetingRoomReserveRecord {
    private Integer id;

    private Integer roomId;

    private Integer userId;

    private Long reserveStartTime;

    private Long reserveEndTime;

    private Integer inNum;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getReserveStartTime() {
        return reserveStartTime;
    }

    public void setReserveStartTime(Long reserveStartTime) {
        this.reserveStartTime = reserveStartTime;
    }

    public Long getReserveEndTime() {
        return reserveEndTime;
    }

    public void setReserveEndTime(Long reserveEndTime) {
        this.reserveEndTime = reserveEndTime;
    }

    public Integer getInNum() {
        return inNum;
    }

    public void setInNum(Integer inNum) {
        this.inNum = inNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}