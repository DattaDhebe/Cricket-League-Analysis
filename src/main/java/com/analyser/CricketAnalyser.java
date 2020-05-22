package com.analyser;

import com.censusanalyser.CSVBuilderException;
import com.censusanalyser.CSVBuilderFactory;
import com.censusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class CricketAnalyser {

    List<IplRunSheetDAO> iplRunsSheetCSVList = null;

    public int loadIplMostRunData(String csvFilePath) throws CricketAnalyserException {

        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            iplRunsSheetCSVList = csvBuilder.getCSVFileList(reader, IplRunSheetDAO.class);
            return iplRunsSheetCSVList.size();

        } catch (NoSuchFileException e) {
            throw new CricketAnalyserException("File Not Found", CricketAnalyserException.ExceptionType.FILE_NOT_FOUND);
        } catch(IOException e){
            throw new CricketAnalyserException("CSV file Not Proper", CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch(CSVBuilderException e){
            throw new CricketAnalyserException(e.getMessage(), CricketAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
}
