package org.example.demo.bean;

public class RuleBean {

    private int id;
    private String description;
    private String destination;

    public RuleBean(int id, String description, String destination) {
        this.id = id;
        this.description = description;
        this.destination = destination;
    }

    public RuleBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
