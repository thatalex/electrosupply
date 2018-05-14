package by.alex.web.site.service;

import org.springframework.core.io.Resource;

import java.io.IOException;

public interface JSONService {
    Resource getResource(Object[] objects) throws IOException;
}
