package com.example.uas_mi_10924008;

import java.util.List;

public class ResponseModel {
    private boolean status;
    private String message;
    private List<MahasiswaModel> data;

    public boolean isStatus() { return status; }
    public String getMessage() { return message; }
    public List<MahasiswaModel> getData() { return data; }
}
