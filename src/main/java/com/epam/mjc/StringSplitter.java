package com.epam.mjc;

import java.util.*;

public class StringSplitter {
    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        Iterator<String> iterator = delimiters.iterator();
        ArrayList<String> list = new ArrayList<>();
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder(source);

        while (iterator.hasNext()) {
            stringTokenizer = new StringTokenizer(stringBuilder.toString(), iterator.next());
            stringBuilder = new StringBuilder();
            while (stringTokenizer.hasMoreElements()) {
                stringBuilder.append(stringTokenizer.nextToken()).append(" ");
            }
        }

        String[] result = stringBuilder.toString().trim().split(" ");
        for (int i = 0; i < result.length; i++) {
            if (!result[i].isEmpty()) {
                list.add(result[i]);
            }
        }
        return list;
    }
}
