package com.opfgroup.demo;

import java.util.List;

public class DemoUtils {

    // For assertArrayEquals
    private String[] firstThreeLettersOfAlphabet = {"A", "B", "C"};

    public String[] getFirstThreeLettersOfAlphabet() {

        return firstThreeLettersOfAlphabet;
    }

    // For assertIterableEquals
    private List<String> academyInList = List.of("luv", "2", "code");

    public List<String> getAcademyInList() {
        return academyInList;
    }
}
