package com.analyser;

public class CricketAnalyserException extends Exception {

    public CricketAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

    public CricketAnalyserException(com.censusanalyser.CensusAnalyserException.ExceptionType noFileFound,
                                    String message) {
    }

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, NO_CENSUS_DATA;
    }

    public CricketAnalyserException.ExceptionType type;

    public CricketAnalyserException(String message, CricketAnalyserException.ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CricketAnalyserException(String message, CricketAnalyserException.ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }


}
