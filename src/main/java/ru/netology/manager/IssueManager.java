package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.domain.IssueComparators;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {
    private final IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue item) {
        repository.save(item);
    }

    public List<Issue> findOpenIssues() {
        List<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (item.isOpen()) {
                result.add(item);
            }
        }
        return result;
    }

    public List<Issue> findClosedIssues() {
        List<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (!item.isOpen()) {
                result.add(item);
            }
        }
        return result;
    }

    public List<Issue> sortByNewest() {
        List<Issue> items = new ArrayList<>(repository.findAll());
        items.sort(new IssueComparators.IssueComparatorByNewest());
        return items;
    }

    public List<Issue> sortByOldest() {
        List<Issue> items = new ArrayList<>(repository.findAll());
        items.sort(new IssueComparators.IssueComparatorByOldest());
        return items;
    }

    public List<Issue> filterByAuthor(String author) {
        Predicate<Issue> byAuthor = issue -> issue.getAuthor().equals(author);
        List<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (byAuthor.test(item)){
                result.add(item);
            }
        }
        return result;
        }

    public List<Issue> filterByLabel(Set<String> label) {
        Predicate<Issue> byLabel = issue -> issue.getLabel().containsAll(label);
        List<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (byLabel.test(item)){
                result.add(item);
            }
        }
        return result;
    }

    public List<Issue> filterByAssignee(Set<String> assignee) {
        Predicate<Issue> byAssignee = issue -> issue.getAssignee().containsAll(assignee);
        List<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (byAssignee.test(item)){
                result.add(item);
            }
        }
        return result;
        }
}
