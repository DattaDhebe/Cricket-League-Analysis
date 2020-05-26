package com.analyser;

import java.util.HashMap;
import java.util.Map;

public class BattingAndBowlingAdapter extends IplFileAdapter {

    Map<String, IplSheetDAO> batsmanData = new HashMap<>();
    Map<String, IplSheetDAO> bowlerData = new HashMap<>();

    @Override
    public Map<String, IplSheetDAO> loadCricketData(CricketAnalyser.Cricket cricket, String... csvFilePath) throws CricketAnalyserException {
        batsmanData = super.loadCricketData(IplRunSheetCSV.class, csvFilePath[0]);
        bowlerData = super.loadCricketData(IplWicketCSV.class, csvFilePath[1]);

        bowlerData.values().stream()
                            .filter(iplLeague -> batsmanData.get(iplLeague.player) != null)
                            .forEach(iplLeague -> {
                                batsmanData.get(iplLeague.player).bowlingAverage = iplLeague.bowlingAverage;
                                batsmanData.get(iplLeague.player).wickets = iplLeague.wickets;
                            });
        return batsmanData;
    }
}
