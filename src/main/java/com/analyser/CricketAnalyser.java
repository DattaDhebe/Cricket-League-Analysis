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

public class CricketAnalyser {


    List<IplRunSheetDAO> iplRunSheetList = null;

    public CricketAnalyser() {
        this.iplRunSheetList = new ArrayList<>();
    }

    public int loadIplMostRunData(String csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplRunSheetCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IplRunSheetCSV.class);
            while (csvFileIterator.hasNext()) {
                this.iplRunSheetList.add(new IplRunSheetDAO(csvFileIterator.next()));
            }
            return this.iplRunSheetList.size();
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

    public int loadIplMostWicketData(String csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplWicketCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IplWicketCSV.class);
            while (csvFileIterator.hasNext()) {
                this.iplRunSheetList.add(new IplRunSheetDAO(csvFileIterator.next()));
            }
            return this.iplRunSheetList.size();
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
        Comparator<IplRunSheetDAO> compareByAverage = Comparator.comparing(cricket -> cricket.average);
        return sort(compareByAverage);
    }

    public String loadSortingOnMaxStrikeRate()  {
        Comparator<IplRunSheetDAO> compareByStrikeRate = Comparator.comparing(cricket -> cricket.strikeRate);
        return sort(compareByStrikeRate);
    }

    public String getSixesWiseSortedData()  {
        Comparator<IplRunSheetDAO> compareBySixes = Comparator.comparing(cricket -> cricket.sixes);
        return sort(compareBySixes);
    }

    public String getFoursWiseSortedData()  {
        Comparator<IplRunSheetDAO> compareByFours = Comparator.comparing(cricket -> cricket.fours);
        return sort(compareByFours);
    }

    public String getStrikeRateWithSixesAndFoursWiseSortedData() {
        Comparator<IplRunSheetDAO> compareByStrikeRate = Comparator.comparing(cricket -> cricket.strikeRate);
        Comparator<IplRunSheetDAO> compareBySixes = compareByStrikeRate.thenComparing(cricket -> cricket.sixes);
        Comparator<IplRunSheetDAO> compareByFours = compareBySixes.thenComparing(cricket -> cricket.fours);
        return sort(compareByFours);
    }

    public String getGreatAverageWithBestStrikingRateWiseSortedData() {
        Comparator<IplRunSheetDAO> compareByAverage = Comparator.comparing(cricket -> cricket.average);
        Comparator<IplRunSheetDAO> compareByStrikeRate = compareByAverage
                                                         .thenComparing(cricket -> cricket.strikeRate);
        return sort(compareByStrikeRate);
    }

    public String getMaximumRunWithBestAverageWiseSortedData() {
        Comparator<IplRunSheetDAO> compareByMaximumRuns = Comparator.comparing(cricket -> cricket.runs);
        Comparator<IplRunSheetDAO> compareByBestAverage = compareByMaximumRuns
                                                          .thenComparing(cricket -> cricket.average);
        return sort(compareByBestAverage);
    }

    public String loadSortingDataOnBowlingAverage()  {
        Comparator<IplRunSheetDAO> compareByAverage = Comparator.comparing(cricket -> cricket.average);
        return sort(compareByAverage);
    }

    public String loadSortingDataOnBowlingStrikingRate()  {
        Comparator<IplRunSheetDAO> compareByStrikingRate = Comparator.comparing(cricket -> cricket.strikeRate);
        return sort(compareByStrikingRate);
    }

    public String loadSortingDataOnBestEconomyRate()  {
        Comparator<IplRunSheetDAO> compareByEconomyRate = Comparator.comparing(cricket -> cricket.economy);
        return sort(compareByEconomyRate);
    }

    public String getStrikeRateWithFiveWicketsAndFourWicketsDataWiseSorted() {
        Comparator<IplRunSheetDAO> compareByStrikingRate = Comparator.comparing(cricket -> cricket.strikeRate);
        Comparator<IplRunSheetDAO> compareByFiveWickets = compareByStrikingRate
                                                          .thenComparing(cricket -> cricket.fiveWickets);
        Comparator<IplRunSheetDAO> compareByFourWickets = compareByFiveWickets
                                                          .thenComparing(cricket -> cricket.fourWickets);
        return sort(compareByFourWickets);
    }

    private String sort(Comparator<IplRunSheetDAO> censusComparator) {
        List sortedData = iplRunSheetList.stream().
                sorted(censusComparator).collect(Collectors.toList());
        return new Gson().toJson(sortedData);
    }

}
