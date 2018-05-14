package by.alex.web.site.service;

import by.alex.web.site.model.Request;

import java.util.List;

public interface RequestService {
    Request save(Request request);

    List<Request> findAll();

    void delete(Integer id);
}
