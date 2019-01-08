package com.xavier.learning.stream;

import com.xavier.learning.model.Artist;
import com.xavier.learning.model.SampleData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamArrayList {
    public static List<Artist> allArtists = SampleData.membersOfTheBeatles;
    public static void main(String[] args) {
        streamFiilter();
    }

    //Stream - > count()
    public static void streamFilterCount() {
        long count = allArtists.stream()
                .filter(artist -> {
                    System.out.println(artist.getName());
                    return artist.isFrom("UK");
                })
                .count();
        System.out.println("Count - " + count);
    }

    //Stream - of > collect -> Stream converted to List
    public static void streamOfCollect() {
        List<String> collected = Stream.of("a", "b","c").collect(Collectors.toList());
    }

    //Stream - map -> convert element
    public static void streamMap(){
        List<String> collected = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(Collectors.toList());
        collected.stream().filter(str -> {System.out.println(str); return true;}).collect(Collectors.toList());
    }

    //Stream - filter -> traverse a list
    public static void streamFiilter(){
        List<String> beginningWithNumbers
                = Stream.of("a", "1abc", "abc1")
                .filter(str -> isDigit(str))
                .collect(Collectors.toList());
        beginningWithNumbers.stream().filter(str -> {System.out.println(str); return true;}).collect(Collectors.toList());
    }

    public static boolean isDigit(String str) {
        char firstChar = str.charAt(0);
        return firstChar >='0' && firstChar <='9';
    }
}
