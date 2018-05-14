package by.alex.web.site.service;

import by.alex.web.site.model.Service;

import java.util.List;

public interface ServiceService {
    Service save(Service request);

    List<Service> findAll();

    Service findOne(Integer id);

    void delete(Integer id);

    byte[] getImage(Integer id);
}
