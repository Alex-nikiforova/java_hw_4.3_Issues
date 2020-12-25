package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueRepository {
    private final List<Issue> items = new ArrayList<>();

    public void save(Issue item) {
        items.add(item);
    }

    public boolean addAll(Collection<? extends Issue> items) {
        return this.items.addAll(items);
    }

    public Issue findById(int id){
        for(Issue item: items) {
            if(item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public List<Issue> findAll() {
        return items;
    }

    public void removeById(int id) {
        items.removeIf(item -> item.getId() == id);
    }

    public boolean removeAll(Collection<? extends Issue> items) {
        return this.items.removeAll(items);
    }

    public void openById(int id) {
        for (Issue item : items) {
            if (item.getId() == id & !item.isOpen()) {
                item.setOpen(true);
            }
        }
    }

    public void closedById(int id) {
        for (Issue item : items) {
            if (item.getId() == id & item.isOpen()) {
                item.setOpen(false);
            }
        }
    }

}
