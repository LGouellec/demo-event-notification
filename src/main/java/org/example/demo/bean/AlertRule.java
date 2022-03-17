package org.example.demo.bean;

import org.example.demo.avro.Alert;
import org.example.demo.avro.Rule;

public class AlertRule {
    private Alert alert;
    private Rule rule;

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
