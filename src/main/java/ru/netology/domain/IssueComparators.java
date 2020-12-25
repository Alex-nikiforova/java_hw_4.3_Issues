package ru.netology.domain;

import java.util.Comparator;

public class IssueComparators {

    public static class IssueComparatorByNewest implements Comparator <Issue> {
        @Override
        public int compare(Issue o1, Issue o2) {
            return o1.getOpenedDaysAgo() - o2.getOpenedDaysAgo();
        }
    }

    public static class IssueComparatorByOldest implements Comparator <Issue> {
        @Override
        public int compare(Issue o1, Issue o2) {
            return o2.getOpenedDaysAgo() - o1.getOpenedDaysAgo();
        }
    }
}
