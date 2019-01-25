package com.xavier.learning.stream;

import com.xavier.learning.model.Album;
import com.xavier.learning.model.Artist;
import com.xavier.learning.model.SampleData;
import com.xavier.learning.model.Track;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.maxBy;

public class StreamArrayList {
    public static List<Artist> allArtists = SampleData.membersOfTheBeatles;
    public static Album album = SampleData.manyTrackAlbum;
    public static List<Album> albums = asList(SampleData.manyTrackAlbum, SampleData.sampleShortAlbum, SampleData.aLoveSupreme);

    public static void main(String[] args) {
        OptionalTest();
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

    //Stream - flatMap
    public static void streamFlatMap() {
        List<Integer>  together = Stream.of(asList(1,2), asList(3,4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        together.stream().filter(str -> {System.out.println(str); return true;}).collect(Collectors.toList());
    }

    //Stream - min
    public static void streamMin() {
        List<Track> tracks = asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));
        Track shortTrack = tracks.stream()
                .min(comparing(track->track.getLength()))
                .get();
        System.out.println(shortTrack.getName());
    }

    //Stream - max
    public static void streamMax() {
        List<Track> tracks = asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));
        Track longTrack = tracks.stream()
                .max(comparing(track->track.getLength()))
                .get();
        System.out.println(longTrack.getName());
    }

    //Stream - reduce (SUM)
    public static void streamReduceSum() {
        int count = Stream.of(1,2,3)
                .reduce(0, (acc, element) -> acc+element);
        System.out.println(count);
    }

    //Stream - reduce (SUM - expend reduce)
    public static void streamReduceSumExpand() {
        BinaryOperator<Integer> accumulator = (acc, element) -> acc+element;
        int count =accumulator.apply(
            accumulator.apply(
                    accumulator.apply(0,1),
            2),
        3);
        System.out.println(count);
    }

    //Stream - complicated manipulation
    public static void streamGetAlbum() {
        Set<String> origins = album.getMusicians()
                .filter(artist -> artist.getName().startsWith("John"))
                .map(artist -> artist.getNationality())
                .collect(Collectors.toSet());
        origins.stream().filter(str -> {System.out.println(str); return true;}).collect(Collectors.toSet());
    }

    //Stream - forEach
    public static void streamForEach(){
        List<Album> albums = asList(SampleData.aLoveSupreme, SampleData.sampleShortAlbum, SampleData.manyTrackAlbum);
        Set<String> trackNames = new HashSet<>();
        albums.stream()
                .forEach(album -> {
                    album.getTracks()
                            .forEach(track -> {
                                if(track.getLength() > 60) {
                                    String name = track.getName();
                                    trackNames.add(name);
                                }
                            });
                });
        trackNames.stream().filter(str -> {System.out.println(str); return true;}).collect(Collectors.toSet());

        Set<String> trackNames2 = new HashSet<>();
        albums.stream()
                .forEach(album1 -> {
                    album1.getTracks()
                            .filter(track -> track.getLength() > 60)
                            .map(track -> track.getName())
                            .forEach(name -> trackNames2.add(name));
                });
        trackNames2.stream().filter(str -> {System.out.println(str); return true;}).collect(Collectors.toSet());

        Set<String> trackNames3 = new HashSet<>();
        albums.stream()
                .flatMap(album2 ->album2.getTracks())
                .filter(track -> track.getLength() > 60)
                .map(track -> track.getName())
                .forEach(name -> trackNames3.add(name));
        trackNames3.stream().filter(str -> {System.out.println(str); return true;}).collect(Collectors.toSet());

        Set<String> trackNames4  = albums.stream()
                .flatMap(album2 ->album2.getTracks())
                .filter(track -> track.getLength() > 60)
                .map(track -> track.getName()).collect(Collectors.toSet());
        trackNames4.stream().filter(str -> {System.out.println(str); return true;}).collect(Collectors.toSet());
    }

    public static List<Album> getThreeAlbumsList(List<Album> albums){
        return albums.stream()
                .filter(album -> album.getTracks().count() <=3)
                .collect(Collectors.toList());
    }

    public static List<String> getNamesAndOrigins(List<Artist> artists) {
        List<String> list =  artists.stream()
                .flatMap(artist->Stream.of(artist.getName()+"-"+artist.getNationality()))
                //map(artist->artist.getName()+"-"+artist.getNationality())
                .collect(Collectors.toList());
        list.forEach(name->System.out.println(name));
        return list;
    }

    public static int getMembersCount(List<Artist> artists) {
        return artists.stream()
                .map(artist -> artist.getMembers().count())
                .reduce(0L, (acc, number) -> acc+number).intValue();
    }

    public static void printTrackLengthStatistics(Album album) {
        IntSummaryStatistics statics = album.getTracks()
                .mapToInt(track -> track.getLength())
                .summaryStatistics();
        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                statics.getMax(),
                statics.getMin(),
                statics.getAverage(),
                statics.getSum());
    }

    public static void OptionalTest() {
        Optional<String> a = Optional.of("a");
        System.out.println(a.get());

        Optional emptyOptional = Optional.empty();
        Optional alsoEmpty = Optional.ofNullable(null);
        System.out.println(a.isPresent());
        System.out.println(emptyOptional.isPresent());
        System.out.println(alsoEmpty.isPresent());

        System.out.println(alsoEmpty.orElse("b"));
        System.out.println(emptyOptional.orElseGet(()->"c"));
        System.out.println(a.orElse("empty"));
    }

    public static int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (acc, x) -> acc + x);
    }


    public static boolean isDigit(String str) {
        char firstChar = str.charAt(0);
        return firstChar >='0' && firstChar <='9';
    }

    public static void sortedList() {
        Set<Integer> number = new HashSet<>(asList(1,2,3,4));
        List<Integer> list =number.stream().sorted()
                .collect(Collectors.toList());

        List<Integer> numbers = Arrays.asList(1,2,3,4);
        List<Integer> stillOrdered = numbers.stream()
                .map(x->x+1)
                .collect(Collectors.toList());
        Set<Integer> unsorted = new HashSet<>(numbers);
        List<Integer> stillUnsorted = unsorted.stream()
                .map(x->x+1)
                .collect(Collectors.toList());
        stillOrdered.stream().collect(Collectors.toList());
        stillOrdered.stream().collect(Collectors.toCollection(TreeSet:new));
    }

    public Optional<Artist> biggestGroup(Stream<Artist> artists) {
        Function<Artist, Long> getCount = artist -> artist.getMembers().count();
        return artists.collect(maxBy(comparing(getCount)));
//        return artists.collect(minBy(comparing(getCount)));
    }

    public double averageNumberOfTracks(List<Album> albums) {
        return albums.stream()
                .collect(averagingInt(album->album.getMusicianList().size()));
    }
}
