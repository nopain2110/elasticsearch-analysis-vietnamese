package com.coccoc.jni;

import java.util.ArrayList;
import java.util.List;

public class NativeSegmentResult {
    private List<NativeToken> tokens = new ArrayList<>();

    public List<NativeToken> getTokens() {
        return tokens;
    }

    public void setTokens(List<NativeToken> tokens) {
        this.tokens = tokens;
    }
}