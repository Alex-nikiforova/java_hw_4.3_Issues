package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmptyRepositoryTest {

    IssueRepository repository = new IssueRepository();

    @Test
    public void shouldReturnEmptyIfFindAllIssues() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyIfFindById() {
        repository.findById(2);

        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = repository.findAll();
        assertEquals(expected, actual);
    }
}
