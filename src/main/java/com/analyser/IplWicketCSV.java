package com.analyser;

import com.opencsv.bean.CsvBindByName;

public class IplWicketCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Runs", required = true)
    public int bowlingRuns;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    @CsvBindByName(column = "Avg", required = true)
    public double bowlingAverage;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;

    @CsvBindByName(column = "SR", required = true)
    public double bowlingStrikeRate;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWickets;

    @CsvBindByName(column = "4w", required = true)
    public int fourWickets;

    public IplWicketCSV() {
    }

    @Override
    public String toString() {
        return "IplWicketCSV{" +
                "player='" + player + '\'' +
                ", bowlingRuns=" + bowlingRuns +
                ", wickets=" + wickets +
                ", bowlingAverage=" + bowlingAverage +
                ", economy=" + economy +
                ", bowlingStrikeRate=" + bowlingStrikeRate +
                ", fiveWickets=" + fiveWickets +
                ", fourWickets=" + fourWickets +
                '}';
    }
}
