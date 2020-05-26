package com.analyser;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;


public class CricketAnalyser {

    private Cricket cricket;

    public enum Cricket { BATTING, BOWLING, BATTING_BOWLING };

    public CricketAnalyser(Cricket cricket) {
        this.cricket = cricket;
    }

    Map<String, IplSheetDAO> iplSheetDAOMap;

    public int loadIplData(String... csvFilePath) throws CricketAnalyserException {
        iplSheetDAOMap = new IplAdapterFactory().cricketFactory(cricket, csvFilePath);
        return this.iplSheetDAOMap.size();
    }

    public String getSortData(SortData.Parameter parameter) {
        Comparator<IplSheetDAO> iplComparator;
        iplComparator = SortData.getArgument(parameter);
        List sortedData = iplSheetDAOMap.values().stream()
                .sorted(iplComparator)
                .collect(Collectors.toList());
        return new Gson().toJson(sortedData);
    }

}
