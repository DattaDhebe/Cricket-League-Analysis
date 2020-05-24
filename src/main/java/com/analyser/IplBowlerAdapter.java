package com.analyser;

import java.util.Map;

public class IplBowlerAdapter extends IplFileAdapter {

    @Override
    public Map<String, IplSheetDAO> loadCricketData(CricketAnalyser.Cricket cricket, String... csvFilePath)
                                                    throws CricketAnalyserException {
        Map<String, IplSheetDAO> recordDAOMap = super.loadCricketData(IplWicketCSV.class, csvFilePath);
        return recordDAOMap;
    }
}
