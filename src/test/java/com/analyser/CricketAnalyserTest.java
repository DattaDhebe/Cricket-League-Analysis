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
        int noOfRecord = cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BATTING, MOST_RUN_CSV_FILE_PATH);
        Assert.assertEquals(100, noOfRecord);
    }

    @Test
    public void givenWrongFilePath_ShouldReturnCustomException() {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BATTING, WRONG_CSV_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenWrongFileExtension_ShouldReturnCustomException() {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BATTING, WRONG_CSV_FILE_EXTENSION);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenWrongDelimiter_ShouldReturnCustomException() {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BATTING, EMPTY_CSV_FILE);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenEmptyFile_ShouldReturnCustomException()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BATTING, WRONG_DELIMITER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenCSVFile_shouldReturn_averageBattingResult()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BATTING, MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.loadSortingOnBattingAverage();
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_maxStrikeRatePlayer()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BATTING, MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.loadSortingOnMaxStrikeRate();
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHitsMaximumSixes()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BATTING, MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSixesWiseSortedData();
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHitsMaximumFours()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BATTING, MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getFoursWiseSortedData();
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("Shikhar Dhawan", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHasHavingBestStrikeRate_andMaximumSixesAndFours()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BATTING, MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getStrikeRateWithSixesAndFoursWiseSortedData();
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHasGreateAverage_withBestStrikingRate()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BATTING, MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getGreatAverageWithBestStrikingRateWiseSortedData();
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHitsMaximumRunWithBestAverage()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BATTING, MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getMaximumRunWithBestAverageWiseSortedData();
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("David Warner", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenIplMostWicket_whenLoaded_shouldReturnNumberOfRecords() throws CricketAnalyserException {
        int noOfRecord = cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, MOST_WICKET_CSV_FILE_PATH);
        Assert.assertEquals(99, noOfRecord);
    }

    @Test
    public void givenWrongMostWicketPath_ShouldReturnCustomException() {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, WRONG_CSV_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenWrongMostWicketPath_whenWrongFileExtension_ShouldReturnCustomException() {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, WRONG_CSV_FILE_EXTENSION);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenEmptyCSVFile_ShouldReturnCustomException() {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, EMPTY_CSV_FILE);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenWrongDelimiterForWicketFile_ShouldReturnCustomException()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, WRONG_DELIMITER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenCSVFile_shouldReturn_topBowlingAverageResult()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.loadSortingDataOnBowlingAverage();
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_topStrikingRateOfBowlers()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.loadSortingDataOnBowlingStrikingRate();
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_bestEconomyRate()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.loadSortingDataOnBestEconomyRate();
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Ben Cutting", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWithBestStrikeRateWith5wAnd4wData()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getStrikeRateWithFiveWicketsAndFourWicketsDataWiseSorted();
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Alzarri Joseph", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWithGreatBowlingAverageAndBestStrikeRateData()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getGreatBowlingAverageWithBestStrikeRateDataWiseSorted();
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWhoTookMaximumWicketsData()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortedDataOnWhoTookMaximumWickets();
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Imran Tahir", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWhoHadBestBowlingAndBattingAverage()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortedDataOnWhoBestBowlingAndBattingAverage();
            IplSheetDAO[] iplSheetDAOS = new Gson().fromJson(sortedWicketData, IplSheetDAO[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplSheetDAOS[iplSheetDAOS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWhoHadMostRunsAndMostWickets()  {
        try {
            cricketAnalyser.loadIplData(CricketAnalyser.Cricket.BOWLING, MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortedDataOnMostRunsAndMostWickets();
            IplSheetDAO[] iplSheetDAOS = new Gson().fromJson(sortedWicketData, IplSheetDAO[].class);
            Assert.assertEquals("Imran Tahir", iplSheetDAOS[iplSheetDAOS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

}
