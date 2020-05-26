package com.analyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyserTest {

    private static final String MOST_RUN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String MOST_WICKET_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_EXTENSION = "./src/test/resources/IPL2019FactsheetMostRuns.txt";
    private static final String EMPTY_CSV_FILE = "./src/test/resources/EmptyFile.csv";

    @Test
    public void givenIplMostRun_whenLoaded_shouldReturnNumberOfRecords() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
        int noOfRecord = cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH);
        Assert.assertEquals(100, noOfRecord);
    }

    @Test
    public void givenWrongFilePath_ShouldReturnCustomException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(WRONG_CSV_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.INVALID_FILE, e.type);
        }
    }

    @Test
    public void givenWrongFileExtension_ShouldReturnCustomException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(WRONG_CSV_FILE_EXTENSION);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenCSVFile_shouldReturn_averageBattingPlayer()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortData(SortData.Parameter.BATTING_AVERAGE);
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHasMaximumRunsPlayer()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortData(SortData.Parameter.BATTING_RUNS);
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("David Warner", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_maximumStrikeRatePlayer()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortData(SortData.Parameter.STRIKE_RATE);
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_minimumStrikeRatePlayer()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortData(SortData.Parameter.STRIKE_RATE);
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("Bhuvneshwar Kumar", iplRunSheetCSVS[0].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHitsMaximumSixes()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortData(SortData.Parameter.SIXES);
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHitsMinimumSixes()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortData(SortData.Parameter.SIXES);
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("Kuldeep Yadav", iplRunSheetCSVS[0].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHitsMaximumFours()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortData(SortData.Parameter.FOURS);
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("Shikhar Dhawan", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHitsMinimumFours()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortData(SortData.Parameter.FOURS);
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("Tim Southee", iplRunSheetCSVS[0].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHasHavingBestStrikeRate_andMaximumSixesAndFours()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortData(SortData.Parameter.BEST_STRIKE_RATE_MAXIMUM_SIXES_FOURS);
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHasGreatAverage_withBestStrikingRate()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortData(SortData.Parameter.GREAT_AVERAGE_BEST_STRIKE_RATE);
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_whoHitsMaximumRunWithBestAverage()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortData(SortData.Parameter.MAXIMUM_RUN_WITH_BATTING_AVERAGE);
            IplRunSheetCSV[] iplRunSheetCSVS = new Gson().fromJson(sortedRunsData, IplRunSheetCSV[].class);
            Assert.assertEquals("David Warner", iplRunSheetCSVS[iplRunSheetCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenIplMostWicket_whenLoaded_shouldReturnNumberOfRecords() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
        int noOfRecord = cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
        Assert.assertEquals(99, noOfRecord);
    }

    @Test
    public void givenWrongMostWicketPath_ShouldReturnCustomException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(WRONG_CSV_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.INVALID_FILE, e.type);
        }
    }

    @Test
    public void givenWrongMostWicketPath_whenWrongFileExtension_ShouldReturnCustomException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(WRONG_CSV_FILE_EXTENSION);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenEmptyCSVFile_ShouldReturnCustomException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(EMPTY_CSV_FILE);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenCSVFile_shouldReturn_topBowlingAveragePlayer()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.BOWLING_AVERAGE);
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_topStrikingRateOfBowlers()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.BOWLING_STRIKE_RATE);
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_minimumStrikingRateOfBowlers()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.BOWLING_STRIKE_RATE);
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Suresh Raina", iplWicketCSVS[0].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_bestEconomyRateOfPlayer()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.ECONOMY);
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Ben Cutting", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_worstEconomyRateOfPlayer()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.ECONOMY);
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Shivam Dube", iplWicketCSVS[0].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWithBestStrikeRateWith5wAnd4wData()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.FIVE_FOUR_WICKETS_STRIKE_RATE);
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Alzarri Joseph", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWithGreatBowlingAverageAndBestStrikeRateData()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.BEST_BOWLING_AVERAGE_STRIKE_RATE);
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWhoTookMaximumFiveWickets()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.FIVE_WICKETS);
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Alzarri Joseph", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWhoTookMinimumFiveWickets()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.FIVE_WICKETS);
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Umesh Yadav", iplWicketCSVS[0].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWhoTookMaximumFourWickets()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.FOUR_WICKETS);
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Kagiso Rabada", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWhoTookMinimumFourWickets()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.FOUR_WICKETS);
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Umesh Yadav", iplWicketCSVS[0].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWhoTookMaximumWicketsData()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BOWLING);
            cricketAnalyser.loadIplData(MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.WICKETS);
            IplWicketCSV[] iplWicketCSVS = new Gson().fromJson(sortedWicketData, IplWicketCSV[].class);
            Assert.assertEquals("Imran Tahir", iplWicketCSVS[iplWicketCSVS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWhoHadBestBowlingAndBattingAverage()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING_BOWLING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH, MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.BEST_BOWLING_BATTING_AVERAGE);
            IplSheetDAO[] iplSheetDAOS = new Gson().fromJson(sortedWicketData, IplSheetDAO[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplSheetDAOS[iplSheetDAOS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWhoHadMostRunsAndMostWickets()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING_BOWLING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH, MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.BEST_ALL_ROUNDER);
            IplSheetDAO[] iplSheetDAOS = new Gson().fromJson(sortedWicketData, IplSheetDAO[].class);
            Assert.assertEquals("David Warner", iplSheetDAOS[iplSheetDAOS.length-1].player);
        } catch (CricketAnalyserException ignore) { }
    }

    @Test
    public void givenCSVFile_shouldReturn_playerWhoHadLeastRunsAndLeastWickets()  {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.BATTING_BOWLING);
            cricketAnalyser.loadIplData(MOST_RUN_CSV_FILE_PATH, MOST_WICKET_CSV_FILE_PATH);
            String sortedWicketData = cricketAnalyser.getSortData(SortData.Parameter.BEST_ALL_ROUNDER);
            IplSheetDAO[] iplSheetDAOS = new Gson().fromJson(sortedWicketData, IplSheetDAO[].class);
            Assert.assertEquals("Tim Southee", iplSheetDAOS[0].player);
        } catch (CricketAnalyserException ignore) { }
    }

}
