package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneManagerTest {

    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);

    private final Issue issue1 = new Issue(1, true, 6, "Victoria", Set.of("label1", "label2", "label3"), Set.of("Victoria", "Maria", "Sasha"));

    @BeforeEach
    public void setUp() {
        manager.add(issue1);
    }

    @Test
    public void shouldFindOpenIssues() {

        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = manager.findOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindClosedIssues() {
        repository.closedById(1);

        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = manager.findClosedIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoNotFindOpenIssueIfAllClose() {
        repository.closedById(1);

        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.findOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoNotFindClosedIssueIfAllOpen() {

        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.findClosedIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWithFilterByAuthor() {

        List<Issue> actual = manager.filterByAuthor("Victoria");
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindItemWithFilterByNonExistentAuthor() {

        List<Issue> actual = manager.filterByAuthor("Viki");
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWithFilterByLabel() {

        List<Issue> actual = manager.filterByLabel(Set.of("label1"));
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWithFilterByLabels() {

        List<Issue> actual = manager.filterByLabel(Set.of("label1", "label2"));
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindItemWithFilterByNonExistentLabel() {

        List<Issue> actual = manager.filterByLabel(Set.of("label11"));
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWithFilterByAssignee() {

        List<Issue> actual = manager.filterByAssignee(Set.of("Maria"));
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWithFilterByAssignees() {

        List<Issue> actual = manager.filterByAssignee(Set.of("Victoria", "Sasha"));
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindItemWithFilterByNonExistentAssignee() {

        List<Issue> actual = manager.filterByAssignee(Set.of("Vera"));
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }
}
