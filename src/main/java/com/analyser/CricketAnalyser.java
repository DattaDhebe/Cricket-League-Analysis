package com.analyser;

import com.censusanalyser.CSVBuilderException;
import com.censusanalyser.CSVBuilderFactory;
import com.censusanalyser.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketAnalyser {


    Map<String, IplSheetDAO> iplRunSheetMap;

    public CricketAnalyser() {
        this.iplRunSheetMap = new HashMap<>();
    }

    public int loadIplMostRunData(String csvFilePath) throws CricketAnalyserException {
        return loadCricketData(csvFilePath, IplRunSheetCSV.class);
    }

    public int loadIplMostWicketData(String csvFilePath) throws CricketAnalyserException {
        return loadCricketData(csvFilePath, IplWicketCSV.class);
    }

    private <E> int loadCricketData(String csvFilePath, Class<E> iplCSVClass) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, iplCSVClass);
            Iterable<E> csvIterable=()->csvFileIterator;
            if (iplCSVClass.getName().equals("com.analyser.IplRunSheetCSV")) {
                StreamSupport.stream(csvIterable.spliterator(),false)
                        .map(IplRunSheetCSV.class::cast)
                        .forEach(iplRunsCSV -> iplRunSheetMap.put(iplRunsCSV.player,new IplSheetDAO(iplRunsCSV)));
            } else if (iplCSVClass.getName().equals("com.analyser.IplWicketCSV")) {
                StreamSupport.stream(csvIterable.spliterator(),false)
                        .map(IplWicketCSV.class::cast)
                        .forEach(iplRunsCSV -> iplRunSheetMap.put(iplRunsCSV.player,new IplSheetDAO(iplRunsCSV)));
            }
            return this.iplRunSheetMap.size();
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.NO_CENSUS_DATA);
        } catch (RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public String loadSortingOnBattingAverage()  {
        Comparator<IplSheetDAO> compareByAverage = Comparator.comparing(cricket -> cricket.battingAverage);
        return sort(compareByAverage);
    }

    public String loadSortingOnMaxStrikeRate()  {
        Comparator<IplSheetDAO> compareByStrikeRate = Comparator.comparing(cricket -> cricket.strikeRate);
        return sort(compareByStrikeRate);
    }

    public String getSixesWiseSortedData()  {
        Comparator<IplSheetDAO> compareBySixes = Comparator.comparing(cricket -> cricket.sixes);
        return sort(compareBySixes);
    }

    public String getFoursWiseSortedData()  {
        Comparator<IplSheetDAO> compareByFours = Comparator.comparing(cricket -> cricket.fours);
        return sort(compareByFours);
    }

    public String getStrikeRateWithSixesAndFoursWiseSortedData() {
        Comparator<IplSheetDAO> compareByStrikeRate = Comparator.comparing(cricket -> cricket.strikeRate);
        Comparator<IplSheetDAO> compareBySixes = compareByStrikeRate.thenComparing(cricket -> cricket.sixes);
        Comparator<IplSheetDAO> compareByFours = compareBySixes.thenComparing(cricket -> cricket.fours);
        return sort(compareByFours);
    }

    public String getGreatAverageWithBestStrikingRateWiseSortedData() {
        Comparator<IplSheetDAO> compareByAverage = Comparator.comparing(cricket -> cricket.battingAverage);
        Comparator<IplSheetDAO> compareByStrikeRate = compareByAverage
                                                         .thenComparing(cricket -> cricket.strikeRate);
        return sort(compareByStrikeRate);
    }

    public String getMaximumRunWithBestAverageWiseSortedData() {
        Comparator<IplSheetDAO> compareByMaximumRuns = Comparator.comparing(cricket -> cricket.battingRuns);
        Comparator<IplSheetDAO> compareByBestAverage = compareByMaximumRuns
                                                          .thenComparing(cricket -> cricket.battingAverage);
        return sort(compareByBestAverage);
    }

    public String loadSortingDataOnBowlingAverage()  {
        Comparator<IplSheetDAO> compareByAverage = Comparator.comparing(cricket -> cricket.bowlingAverage);
        return sort(compareByAverage);
    }

    public String loadSortingDataOnBowlingStrikingRate()  {
        Comparator<IplSheetDAO> compareByStrikingRate = Comparator.comparing(cricket -> cricket.strikeRate);
        return sort(compareByStrikingRate);
    }

    public String loadSortingDataOnBestEconomyRate()  {
        Comparator<IplSheetDAO> compareByEconomyRate = Comparator.comparing(cricket -> cricket.economy);
        return sort(compareByEconomyRate);
    }

    public String getStrikeRateWithFiveWicketsAndFourWicketsDataWiseSorted() {
        Comparator<IplSheetDAO> compareByFiveWickets = Comparator.comparing(cricket -> cricket.fiveWickets);
        Comparator<IplSheetDAO> compareByFourWickets = compareByFiveWickets
                                                          .thenComparing(cricket -> cricket.fourWickets);
        return sort(compareByFourWickets);
    }

    public String getGreatBowlingAverageWithBestStrikeRateDataWiseSorted() {
        Comparator<IplSheetDAO> compareByBestAverage = Comparator.comparing(cricket -> cricket.bowlingAverage);
        Comparator<IplSheetDAO> compareByBestStrikeRate = compareByBestAverage
                                                          .thenComparing(cricket -> cricket.strikeRate);
        return sort(compareByBestStrikeRate);
    }

    public String getSortedDataOnWhoTookMaximumWickets() {
        Comparator<IplSheetDAO> compareByMaximumWickets = Comparator.comparing(cricket -> cricket.wickets);
        Comparator<IplSheetDAO> compareByBestBowlingAverage = compareByMaximumWickets
                                                          .thenComparing(cricket -> cricket.bowlingAverage);
        return sort(compareByBestBowlingAverage);
    }

    public String getSortedDataOnWhoBestBowlingAndBattingAverage() {
        Comparator<IplSheetDAO> compareByBattingAverage = Comparator.comparing(cricket -> cricket.battingAverage);
        Comparator<IplSheetDAO> compareByBowlingAverage = compareByBattingAverage
                                                            .thenComparing(cricket -> cricket.bowlingAverage);
        return sort(compareByBowlingAverage);
    }

    public String getSortedDataOnMostRunsAndMostWickets() {
        Comparator<IplSheetDAO> compareByMostRuns = Comparator.comparing(cricket -> cricket.battingRuns);
        Comparator<IplSheetDAO> compareByMostWickets = compareByMostRuns
                                                         .thenComparing(cricket -> cricket.wickets);
        return sort(compareByMostWickets);
    }

    private String sort(Comparator<IplSheetDAO> censusComparator) {
        List sortedData = iplRunSheetMap.values().stream().
                sorted(censusComparator).collect(Collectors.toList());
        return new Gson().toJson(sortedData);
    }

}
