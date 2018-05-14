package by.alex.web.site.service;

import by.alex.web.site.model.Service;
import by.alex.web.site.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service("serviceService")
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Service findOne(Integer id) {
        return serviceRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        serviceRepository.delete(id);
    }

    @Override
    public byte[] getImage(Integer id) {
        Service service = serviceRepository.findOne(id);
        if (service != null) {
            return service.getImage();
        }
        return null;
    }
}
