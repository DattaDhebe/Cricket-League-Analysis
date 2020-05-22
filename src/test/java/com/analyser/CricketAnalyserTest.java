package com.analyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyserTest {

    private static final String MOST_RUN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String MOST_WICKET_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_EXTENSION = "./src/test/resources/IPL2019FactsheetMostRuns.txt";
    private static final String WRONG_DELIMITER = "./src/test/resources/WrongDelimiter.csv";
    private static final String EMPTY_CSV_FILE = "./src/test/resources/EmptyFile.csv";

    CricketAnalyser cricketAnalyser = new CricketAnalyser();

    @Test
    public void givenIplMostRun_whenLoaded_shouldReturnNumberOfRecords() throws CricketAnalyserException {
        int noOfRecord = cricketAnalyser.loadIplMostRunData(MOST_RUN_CSV_FILE_PATH);
        Assert.assertEquals(101, noOfRecord);

    }

    @Test
    public void givenWrongFilePath_ShouldReturnCustomException()  {
        try {
            cricketAnalyser.loadIplMostRunData(WRONG_CSV_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenWrongFileExtension_ShouldReturnCustomException() {
        try {
            cricketAnalyser.loadIplMostRunData(WRONG_CSV_FILE_EXTENSION);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.WRONG_EXTENSION, e.type);
        }
    }

    @Test
    public void givenWrongDelimiter_ShouldReturnCustomException() {
        try {
            cricketAnalyser.loadIplMostRunData(EMPTY_CSV_FILE);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenEmptyFile_ShouldReturnCustomException() throws CricketAnalyserException {
        try {
            cricketAnalyser.loadIplMostRunData(WRONG_DELIMITER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenCSVFile_shouldReturn_averageBattingResult() throws CricketAnalyserException {
        try {
            cricketAnalyser.loadIplMostRunData(MOST_RUN_CSV_FILE_PATH);
            String sortedCricketData = cricketAnalyser.loadSortingOnBattingAverage();
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedCricketData, IplRunSheetCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }

    }

}
