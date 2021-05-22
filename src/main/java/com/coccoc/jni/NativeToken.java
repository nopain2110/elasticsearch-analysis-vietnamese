package com.coccoc.jni;

public class NativeToken {
    private Integer originalStart;
    private Integer originalEnd;
    private Integer type;
    private Integer segType;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getOriginalStart() {
        return originalStart;
    }

    public void setOriginalStart(Integer originalStart) {
        this.originalStart = originalStart;
    }

    public Integer getOriginalEnd() {
        return originalEnd;
    }

    public void setOriginalEnd(Integer originalEnd) {
        this.originalEnd = originalEnd;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSegType() {
        return segType;
    }

    public void setSegType(Integer segType) {
        this.segType = segType;
    }
}
