package com.analyser;

import com.opencsv.bean.CsvBindByName;

public class IplRunSheetCSV {

    @CsvBindByName(column ="PLAYER", required=true)
    public String player;

    @CsvBindByName(column ="Mat", required=true)
    public int match;

    @CsvBindByName(column ="Inns", required=true)
    public int innings;

    @CsvBindByName(column ="NO", required=true)
    public int notOut;

    @CsvBindByName(column ="Runs", required=true)
    public int runs;

    @CsvBindByName(column ="SR", required=true)
    public double strikeRate;

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
                ", match=" + match +
                ", innings=" + innings +
                ", notOut=" + notOut +
                ", runs=" + runs +
                ", strikeRate=" + strikeRate +
                ", average=" + average +
                ", hundreds=" + hundreds +
                ", fiftys=" + fiftys +
                ", sixes=" + sixes +
                ", fours=" + fours +
                '}';
    }
}
