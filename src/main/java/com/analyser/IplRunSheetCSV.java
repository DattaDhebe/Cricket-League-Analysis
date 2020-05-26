package com.analyser;

import com.opencsv.bean.CsvBindByName;

public class IplRunSheetCSV {

    @CsvBindByName(column ="PLAYER", required=true)
    public String player;

    @CsvBindByName(column ="Runs", required=true)
    public int battingRuns;

    @CsvBindByName(column ="SR", required=true)
    public double battingStrikeRate;

    @CsvBindByName(column ="Avg", required=true)
    public double battingAverage;

    @CsvBindByName(column ="6s" , required=true)
    public int sixes;

    @CsvBindByName(column ="4s" , required=true)
    public int fours;

    public IplRunSheetCSV() {
    }

    @Override
    public String toString() {
        return "Ipl2019RunsSheet{" +
                "player='" + player + '\'' +
                ", runs=" + battingRuns +
                ", strikeRate=" + battingStrikeRate +
                ", average=" + battingAverage +
                ", sixes=" + sixes +
                ", fours=" + fours +
                '}';
    }
}
