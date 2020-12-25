package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MultipleItemsRepositoryTest {

    IssueRepository repository = new IssueRepository();

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
    public void shouldFindAll() {

        List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue3, issue4, issue5));
        List<Issue> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindById() {

        Issue actual = repository.findById(4);
        assertEquals(issue4, actual);
    }

    @Test
    public void shouldReturnNullIfFindNonExistentItem() {

        assertNull(repository.findById(7));
    }

    @Test
    public void shouldRemoveById() {
        repository.removeById(3);

        List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue4, issue5));
        List<Issue> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveAllItems() {

        assertTrue(repository.removeAll(List.of(issue1, issue2, issue3, issue4, issue5)));
    }

    @Test
    public void shouldDoNothingIfRemoveNonExistentItem() {
        repository.removeById(6);

        List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue3, issue4, issue5));
        List<Issue> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSetOpenById() {
        repository.openById(5);

        assertTrue(issue5.isOpen());
    }

    @Test
    public void shouldSetCloseById() {
        repository.closedById(4);

        assertFalse(issue4.isOpen());
    }
}