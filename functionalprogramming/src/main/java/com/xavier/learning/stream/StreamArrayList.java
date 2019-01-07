package com.xavier.learning.stream;

import com.xavier.learning.model.Artist;
import com.xavier.learning.model.SampleData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamArrayList {
    public static void main(String[] args) {
        List<Artist> allArtists = SampleData.membersOfTheBeatles;
        //Stream - > count()
        long count = allArtists.stream()
                .filter(artist -> {
                    System.out.println(artist.getName());
                    return artist.isFrom("UK");
                })
                .count();
        System.out.println("Count - " + count);

        //Stream - of > collect -> Stream converted to List
        List<String> collected = Stream.of("a", "b","c").collect(Collectors.toList());
    }
}
