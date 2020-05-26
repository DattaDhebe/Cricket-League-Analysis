package com.analyser;

import java.util.Map;

public class IplAdapterFactory {

    public Map<String, IplSheetDAO> cricketFactory(CricketAnalyser.Cricket cricket, String... csvFilePath)
                                                  throws CricketAnalyserException {
        if (cricket.equals(cricket.BATTING))
            return new IplBatsmanAdapter().loadCricketData(cricket, csvFilePath);

        if(cricket.equals(cricket.BOWLING))
            return new IplBowlerAdapter().loadCricketData(cricket, csvFilePath);

        return new BattingAndBowlingAdapter().loadCricketData(cricket, csvFilePath);
    }
}
