package com.coccoc;

import java.util.ArrayList;
import java.util.List;

public class NativeSegmentResult {
    private List<Token> tokens = new ArrayList<>();

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
}