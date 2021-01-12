package com.example.spinner;

public class SpinnerBean {
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SpinnerBean{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}