package com.xavier.learning.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DefaultMethodAndChildClass {
    public static void main(String[] args) {
        Parent parent = new ParentImpl();
        parent.welcome();
        System.out.println("-----"+parent.getLastMessage()+"-----");

        Child child = new ChildImpl();
        child.welcome();
        System.out.println("-----"+child.getLastMessage()+"-----");

        Parent childOverridingParent = new OverridingParent();
        childOverridingParent.welcome();
        System.out.println("-----"+childOverridingParent.getLastMessage()+"-----");

        Child inhreritedConcreteClassImplementInterface = new InhreritedConcreteClassImplementInterface();
        inhreritedConcreteClassImplementInterface.welcome();
        System.out.println("-----"+inhreritedConcreteClassImplementInterface.getLastMessage()+"-----");

        Jukebox box = new MusicalCarriage();
        Carriage box2 = new MusicalCarriage();
        System.out.println(box.rock());
        System.out.println(box2.rock());

    }
}
