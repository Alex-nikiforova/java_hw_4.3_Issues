package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MultipleItemsManagerTest {

    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);

    private final Issue issue1 = new Issue(1, true, 6, "Victoria", Set.of("label1", "label2", "label3"), Set.of("Victoria", "Maria", "Sasha"));
    private final Issue issue2 = new Issue(2, true, 5, "Sasha", Set.of("label5", "label1", "label6"), Set.of("Victoria", "Sasha"));
    private final Issue issue3 = new Issue(3, false, 1, "Victoria", Set.of("label2", "label5"), Set.of("Vova", "Maria", "Tim"));
    private final Issue issue4 = new Issue(4, true, 10, "Ivan", Set.of("label1", "label3", "label6"), Set.of("Ivan", "Maria"));
    private final Issue issue5 = new Issue(5, false, 8, "Vova", Set.of("label5", "label6"), Set.of("Victoria", "Vova"));

    @BeforeEach
    public void setUp() {
        repository.addAll(List.of(issue1, issue2, issue3, issue4, issue5));
    }

    @Test
    public void shouldFindOpenIssues() {

        List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue4));
        List<Issue> actual = manager.findOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindClosedIssues() {

        List<Issue> expected = new ArrayList<>(List.of(issue3, issue5));
        List<Issue> actual = manager.findClosedIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoNotFindOpenIssuesIfAllClosed() {
        repository.closedById(1);
        repository.closedById(2);
        repository.closedById(4);

        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.findOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoNotFindClosedIssuesIfAllOpen() {
        repository.openById(3);
        repository.openById(5);

        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.findClosedIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSortByNewest() {

        List<Issue> expected = new ArrayList<>(List.of(issue3, issue2, issue1, issue5, issue4));
        List<Issue> actual = manager.sortByNewest();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSortByOldest() {

        List<Issue> expected = new ArrayList<>(List.of(issue4, issue5, issue1, issue2,issue3));
        List<Issue> actual = manager.sortByOldest();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWithFilterByAuthor() {

        List<Issue> actual = manager.filterByAuthor("Victoria");
        List<Issue> expected = new ArrayList<>(List.of( issue1, issue3));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindNonExistentItemWithFilterByAuthor() {

        List<Issue> actual = manager.filterByAuthor("Viki");
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWithFilterByLabel() {

        List<Issue> actual = manager.filterByLabel(Set.of("label1"));
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue4));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWithFilterByLabels() {

        List<Issue> actual = manager.filterByLabel(Set.of("label5", "label6"));
        List<Issue> expected = new ArrayList<>(List.of(issue2, issue5));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindNonExistentItemWithFilterByLabel() {

        List<Issue> actual = manager.filterByLabel(Set.of("label11"));
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWithFilterByAssignee() {

        List<Issue> actual = manager.filterByAssignee(Set.of("Maria"));
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue3, issue4));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWithFilterByAssignees() {

        List<Issue> actual = manager.filterByAssignee(Set.of("Victoria", "Sasha"));
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue2));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindNonExistentItemWithFilterByAssignee() {

        List<Issue> actual = manager.filterByAssignee(Set.of("Vera"));
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }
}