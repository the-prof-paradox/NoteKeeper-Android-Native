package com.example.experiment;

public class NoteModel {
    private String dateTime;
    private String note;

    public NoteModel(String dateTime, String note){
        this.dateTime = dateTime;
        this.note = note;
    }

    @Override
    public String toString() {
        return "NoteModel{" +
                "dateTime=" + dateTime +
                ", note= '"+ note + '\'' +
                '}';
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
