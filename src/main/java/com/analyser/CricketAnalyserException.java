package com.analyser;

public class CricketAnalyserException extends Exception {

    public enum ExceptionType {
        CRICKET_FILE_PROBLEM, NO_CRICKET_DATA, INVALID_FILE;

    }

    public CricketAnalyserException.ExceptionType type;

    public CricketAnalyserException(String message, CricketAnalyserException.ExceptionType type) {
        super(message);
        this.type = type;
    }
}
