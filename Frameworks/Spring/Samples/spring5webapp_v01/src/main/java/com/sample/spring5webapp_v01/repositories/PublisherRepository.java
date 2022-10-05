package com.sample.spring5webapp_v01.repositories;

import com.sample.spring5webapp_v01.models.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
