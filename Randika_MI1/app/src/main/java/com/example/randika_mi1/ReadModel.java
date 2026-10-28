package com.example.randika_mi1;

import java.io.Serializable;
import java.util.List;

public class ReadModel {
    private List<Data> result;

    public List<Data> getResult() {
        return result;
    }

    public void setResult(List<Data> result) {
        this.result = result;
    }

    public static class Data implements Serializable {
        private String id;
        private String name;
        private String number;

        public Data(String id, String name, String number) {
            this.id = id;
            this.name = name;
            this.number = number;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
