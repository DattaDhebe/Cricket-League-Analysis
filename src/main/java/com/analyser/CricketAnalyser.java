package com.analyser;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;


public class CricketAnalyser {

    public enum Cricket { BATTING, BOWLING };

    Map<String, IplSheetDAO> iplRunSheetMap;

    public CricketAnalyser() {    }

    public int loadIplData(Cricket cricket, String... csvFilePath) throws CricketAnalyserException {
        iplRunSheetMap = new IplAdapterFactory().cricketFactory(cricket, csvFilePath);
        return this.iplRunSheetMap.size();
    }

    public String getSortData(SortData.Parameter parameter) {
        Comparator<IplSheetDAO> iplComparator;
        iplComparator = SortData.getArgument(parameter);
        List sortedData = iplRunSheetMap.values().stream().
                sorted(iplComparator).collect(Collectors.toList());
        return new Gson().toJson(sortedData);
    }

}
