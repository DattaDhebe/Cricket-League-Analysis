package com.analyser;

import java.util.Map;

public class IplAdapterFactory {

    public Map<String, IplSheetDAO> CricketFactory(CricketAnalyser.Cricket cricket, String... csvFilePath)
                                                  throws CricketAnalyserException {
        if (cricket.equals(cricket.BATTING))
            return new IplBatsmanAdapter().loadCricketData(cricket, csvFilePath);
        return new IplBowlerAdapter().loadCricketData(cricket, csvFilePath);
    }
}
