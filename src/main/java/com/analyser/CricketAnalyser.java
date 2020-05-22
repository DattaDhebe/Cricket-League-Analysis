package com.analyser;

import com.censusanalyser.CSVBuilderException;
import com.censusanalyser.CSVBuilderFactory;
import com.censusanalyser.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketAnalyser {


    List<IplRunSheetDAO> censusList = null;

    public CricketAnalyser() {
        this.censusList = new ArrayList<>();
    }

    public int loadIplMostRunData(String csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplRunSheetCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IplRunSheetCSV.class);
            while (csvFileIterator.hasNext()) {
                this.censusList.add(new IplRunSheetDAO(csvFileIterator.next()));
            }
            return this.censusList.size();
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
    }

    public String loadSortingOnBattingAverage()  {
        Comparator<IplRunSheetDAO> censusComparator = Comparator.comparing(cricket -> cricket.average);
        List sortedDataByAverage = censusList.stream().
                sorted(censusComparator).collect(Collectors.toList());
        return new Gson().toJson(sortedDataByAverage);

    }

    public String loadSortingOnMaxStrikeRate()  {
        Comparator<IplRunSheetDAO> censusComparator = Comparator.comparing(cricket -> cricket.strikeRate);
        List sortedDataByStrikeRate = censusList.stream().
                sorted(censusComparator).collect(Collectors.toList());
        return new Gson().toJson(sortedDataByStrikeRate);

    }

    public String getSixesWiseSortedData()  {
        Comparator<IplRunSheetDAO> censusComparator = Comparator.comparing(cricket -> cricket.sixes);
        List sortedDataBySixes = censusList.stream().
                sorted(censusComparator).collect(Collectors.toList());
        return new Gson().toJson(sortedDataBySixes);

    }

    public String getFoursWiseSortedData()  {
        Comparator<IplRunSheetDAO> censusComparator = Comparator.comparing(cricket -> cricket.fours);
        List sortedDataByFours = censusList.stream().
                sorted(censusComparator).collect(Collectors.toList());
        return new Gson().toJson(sortedDataByFours);

    }

    public String getStrikeRateWithSixesAndFoursWiseSortedData() {
        Comparator<IplRunSheetDAO> compareByStrikeRate = Comparator.comparing(cricket -> cricket.strikeRate);
        Comparator<IplRunSheetDAO> compareBySixes = compareByStrikeRate.thenComparing(cricket -> cricket.sixes);
        Comparator<IplRunSheetDAO> compareByFours = compareBySixes.thenComparing(cricket -> cricket.fours);
        List sortedDataByStrikeRateWithSixesAndFours = censusList.stream().
                sorted(compareByFours).collect(Collectors.toList());
        return new Gson().toJson(sortedDataByStrikeRateWithSixesAndFours);
    }

    public String getGreatAverageWithBestStrikingRateWiseSortedData() {
        Comparator<IplRunSheetDAO> compareByAverage = Comparator.comparing(cricket -> cricket.average);
        Comparator<IplRunSheetDAO> compareByStrikeRate = compareByAverage.thenComparing(cricket -> cricket.strikeRate);
        List sortedDataByGreatAverageWithStrikeRate = censusList.stream().
                sorted(compareByStrikeRate).collect(Collectors.toList());
        return new Gson().toJson(sortedDataByGreatAverageWithStrikeRate);
    }


}
