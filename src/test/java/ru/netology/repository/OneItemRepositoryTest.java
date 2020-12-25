package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OneItemRepositoryTest {
    IssueRepository repository = new IssueRepository();

    private final Issue issue1 = new Issue(1, true, 6, "Victoria", Set.of("label1", "label2", "label3"), Set.of("Victoria", "Maria", "Sasha"));

    @BeforeEach
    public void setUp() {
        repository.save(issue1);
    }

    @Test
    public void shouldFindAll() {

        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindById() {

        Issue actual = repository.findById(1);
        assertEquals(issue1, actual);
    }

    @Test
    public void shouldReturnNullIfFindNonExistentItem() {

        assertNull(repository.findById(7));
    }

    @Test
    public void shouldRemoveById() {
        repository.removeById(1);

        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveAllItems() {

        assertTrue(repository.removeAll(List.of(issue1)));
    }

    @Test
    public void shouldDoNothingIfRemoveNonExistentItem() {
        repository.removeById(2);


        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSetCloseById() {
        repository.closedById(1);

        assertFalse(issue1.isOpen());
    }

    @Test
    public void shouldSetOpenById() {
        repository.closedById(1);
        repository.openById(1);

        assertTrue(issue1.isOpen());
    }
}
