package com.analyser;

import com.censusanalyser.CSVBuilderException;
import com.censusanalyser.CSVBuilderFactory;
import com.censusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IplFileAdapter {

    public abstract Map<String, IplSheetDAO> loadCricketData(String... csvFilePath) throws CricketAnalyserException;

    public  <E> Map<String, IplSheetDAO> loadCricketData(Class<E> iplCSVClass, String... csvFilePath)
            throws CricketAnalyserException {
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
                    CricketAnalyserException.ExceptionType.INVALID_FILE);
        } catch (CSVBuilderException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.NO_CRICKET_DATA);
        } catch (RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }


}
