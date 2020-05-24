package com.analyser;

import java.util.Map;

public class IplBatsmanAdapter extends IplFileAdapter {

    @Override
    public Map<String, IplSheetDAO> loadCricketData(CricketAnalyser.Cricket cricket, String... csvFilePath)
                                                    throws CricketAnalyserException {
        Map<String, IplSheetDAO> iplSheetDAOMap = super.loadCricketData(IplRunSheetCSV.class, csvFilePath[0]);
        return iplSheetDAOMap;
    }
}
