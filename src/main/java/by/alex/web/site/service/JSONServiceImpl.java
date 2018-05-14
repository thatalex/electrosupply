package by.alex.web.site.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service("JSONService")
public class JSONServiceImpl implements JSONService {

    @Override
    public Resource getResource(Object[] objects) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = objectMapper.writeValueAsBytes(objects);
        try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
            Resource resource = new InputStreamResource(inputStream);
            return resource;
        }
    }

}
