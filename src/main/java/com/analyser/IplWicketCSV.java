package com.analyser;

import com.opencsv.bean.CsvBindByName;

public class IplWicketCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Runs")
    public int runs;

    @CsvBindByName(column = "Wkts")
    public int wickets;

    @CsvBindByName(column = "Avg")
    public double average;

    @CsvBindByName(column = "Econ")
    public double economy;

    @CsvBindByName(column = "SR")
    public double bowlingStrikeRate;

    @CsvBindByName(column = "5w")
    public int fiveWickets;

    @CsvBindByName(column = "4w")
    public int fourWickets;

    @Override
    public String toString() {
        return "Ipl2019WicketsSheetCSV{" +
                "player='" + player + '\'' +
                ", runs=" + runs +
                ", wickets=" + wickets +
                ", average=" + average +
                ", economy=" + economy +
                ", strikeRate=" + bowlingStrikeRate +
                ", fiveWickets=" + fiveWickets +
                ", fourWickets=" + fourWickets +
                '}';
    }
}
