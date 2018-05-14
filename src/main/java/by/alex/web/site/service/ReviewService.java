package by.alex.web.site.service;

import by.alex.web.site.model.Review;

import java.util.List;

public interface ReviewService {
    Review save(Review request);

    List<Review> findAll();
}
