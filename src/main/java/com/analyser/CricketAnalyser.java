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
        this.censusList = new ArrayList<IplRunSheetDAO>();
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

    public String loadSortingOnBattingAverage() throws CricketAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CricketAnalyserException("No Census Data", CricketAnalyserException.ExceptionType.NO_CENSUS_DATA);

        }
        Comparator<IplRunSheetDAO> censusComparator = Comparator.comparing(cricket -> cricket.average);
        List sortedDataByAverage = censusList.stream().
                sorted(censusComparator).collect(Collectors.toList());
        String sortedAverageDataInJson = new Gson().toJson(sortedDataByAverage);
        return sortedAverageDataInJson;
    }

    public String loadSortingOnMaxStrikeRate() throws CricketAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CricketAnalyserException("No Census Data", CricketAnalyserException.ExceptionType.NO_CENSUS_DATA);

        }
        Comparator<IplRunSheetDAO> censusComparator = Comparator.comparing(cricket -> cricket.strikeRate);
        List sortedDataByAverage = censusList.stream().
                sorted(censusComparator).collect(Collectors.toList());
        String sortedAverageDataInJson = new Gson().toJson(sortedDataByAverage);
        return sortedAverageDataInJson;
    }

}
