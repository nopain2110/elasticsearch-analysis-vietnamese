package com.coccoc;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Rewrite class com.coccoc.Tokenizer for Elasticsearch integration.
 *
 * @author nopain, duydo, CocCoc team
 */
public class Tokenizer {
    public static final String TOKENIZER_SHARED_LIB_NAME = "coccoc_tokenizer_jni";

    public enum TokenizeOption {
        NORMAL(0),
        HOST(1),
        URL(2);

        private final int value;

        TokenizeOption(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    static {
        System.loadLibrary(TOKENIZER_SHARED_LIB_NAME);
    }

    public Tokenizer(String dictPath) {
        int status = initialize(dictPath);
        if (0 > status) {
            throw new RuntimeException(
                    String.format("Cannot initialize Tokenizer: %s", dictPath)
            );
        }
    }

    public List<Token> segment(String text, TokenizeOption option, boolean keepPunctuation) {
        return segment(text, option, keepPunctuation, false);
    }

    public List<Token> segment(String text, TokenizeOption option, boolean keepPunctuation, boolean forTransforming) {
        return segment(text, forTransforming, option.value(), keepPunctuation);
    }

    private List<Token> segment(String text, boolean forTransforming, int tokenizeOption, boolean keepPunctuation) {
        if (text == null) {
            throw new IllegalArgumentException("text is null");
        }
        String jsonSegmentRes = segmentJson(text, forTransforming, tokenizeOption, keepPunctuation);
        NativeSegmentResult segmentResult = JSON.parseObject(jsonSegmentRes, NativeSegmentResult.class);
        List<Token> tokens = segmentResult.getTokens();

        if (forTransforming && tokenizeOption == TokenizeOption.NORMAL.value()) {
            tokens.add(Token.FULL_STOP);
        }

        return tokens;
    }

    //Calls CocCoc lib's segmentJson function
    public native String segmentJson(String text, boolean forTransforming, int tokenizeOption, boolean keepPunctuation);

    //Calls CocCoc lib's initialize function
    private native int initialize(String dictPath);
}
