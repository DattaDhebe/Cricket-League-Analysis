package com.analyser;

import java.util.Map;

public class IplAdapterFactory extends IplFileAdapter {

    @Override
    public Map<String, IplSheetDAO> loadCricketData(String... csvFilePath) throws CricketAnalyserException {
        return null;
    }

    public Map<String, IplSheetDAO> loadCricketData(CricketAnalyser.Cricket cricket, String... csvFilePath)
                                                        throws CricketAnalyserException {
        if (cricket.equals(CricketAnalyser.Cricket.BATTING))
            return this.loadCricketData(IplRunSheetCSV.class, csvFilePath);
        else if (cricket.equals(CricketAnalyser.Cricket.BOWLING))
            return this.loadCricketData(IplWicketCSV.class, csvFilePath);
        else
            throw new CricketAnalyserException("Invalid File", CricketAnalyserException.ExceptionType.INVALID_FILE);
    }

}
