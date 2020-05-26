package com.analyser;

import com.opencsv.bean.CsvBindByName;

public class IplRunSheetCSV {

    @CsvBindByName(column ="PLAYER", required=true)
    public String player;

    @CsvBindByName(column ="Runs", required=true)
    public int runs;

    @CsvBindByName(column ="SR", required=true)
    public double battingStrikeRate;

    @CsvBindByName(column ="Avg", required=true)
    public double average;

    @CsvBindByName(column ="100", required=true )
    public int hundreds;

    @CsvBindByName(column ="50", required=true)
    public int fiftys;

    @CsvBindByName(column ="6s" , required=true)
    public int sixes;

    @CsvBindByName(column ="4s" , required=true)
    public int fours;

    @Override
    public String toString() {
        return "Ipl2019RunsSheet{" +
                "player='" + player + '\'' +
                ", runs=" + runs +
                ", strikeRate=" + battingStrikeRate +
                ", average=" + average +
                ", hundreds=" + hundreds +
                ", fiftys=" + fiftys +
                ", sixes=" + sixes +
                ", fours=" + fours +
                '}';
    }
}
