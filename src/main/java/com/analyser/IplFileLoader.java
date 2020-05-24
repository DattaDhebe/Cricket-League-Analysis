package com.analyser;

import com.censusanalyser.CSVBuilderException;
import com.censusanalyser.CSVBuilderFactory;
import com.censusanalyser.CensusAnalyserException;
import com.censusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IplFileLoader {

    public  <E> Map<String, IplSheetDAO> loadCricketData(CricketAnalyser.Cricket cricket, String... csvFilePath) throws CricketAnalyserException {
        if (cricket.equals(CricketAnalyser.Cricket.BATTING))
            return this.loadCricketData(IplRunSheetCSV.class, csvFilePath);
        else if (cricket.equals(CricketAnalyser.Cricket.BOWLING))
            return this.loadCricketData(IplWicketCSV.class, csvFilePath);
        else
            throw new CricketAnalyserException("Invalid File", CricketAnalyserException.ExceptionType.INVALID_FILE);
    }


    public  <E> Map<String, IplSheetDAO> loadCricketData(Class<E> iplCSVClass, String... csvFilePath) throws CricketAnalyserException {
        Map<String, IplSheetDAO> iplRunSheetMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]));) {
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
            return iplRunSheetMap;
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

}
