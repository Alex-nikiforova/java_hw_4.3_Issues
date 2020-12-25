package ru.netology.domain;

import java.util.Set;

public class Issue implements Comparable <Issue> {
    private int id;
    private boolean isOpen;
    private int openedDaysAgo;
    private String author;
    private Set<String> label;
    private Set<String> assignee;

    public Issue(int id, boolean isOpen, int openedDaysAgo, String author, Set<String> label, Set<String> assignee) {
        this.id = id;
        this.isOpen = isOpen;
        this.openedDaysAgo = openedDaysAgo;
        this.author = author;
        this.label = label;
        this.assignee = assignee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getOpenedDaysAgo() {
        return openedDaysAgo;
    }

    public void setOpenedDaysAgo(int openedDaysAgo) {
        this.openedDaysAgo = openedDaysAgo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<String> getLabel() {
        return label;
    }

    public void setLabel(Set<String> label) {
        this.label = label;
    }

    public Set<String> getAssignee() {
        return assignee;
    }

    public void setAssignee(Set<String> assignee) {
        this.assignee = assignee;
    }

    @Override
    public int compareTo(Issue o) {
        return id - o.id;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", isOpen=" + isOpen +
                ", openedDaysAgo=" + openedDaysAgo +
                ", author='" + author + '\'' +
                ", label=" + label +
                ", assignee=" + assignee +
                '}';
    }
}
