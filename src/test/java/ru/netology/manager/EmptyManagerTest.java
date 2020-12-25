package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyManagerTest {

    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);

    @Test
    public void shouldReturnEmptyIfFindOpenIssues() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.findOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyIfFindClosedIssues() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.findClosedIssues();
        assertEquals(expected, actual);
    }
}
