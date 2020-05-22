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
        CENSUS_FILE_PROBLEM, WRONG_EXTENSION, NO_CENSUS_DATA, INCORRECT_DELIMITER_EXCEPTION,
        INCORRECT_DELIMITER_HEADER_EXCEPTION, FILE_NOT_FOUND;
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
