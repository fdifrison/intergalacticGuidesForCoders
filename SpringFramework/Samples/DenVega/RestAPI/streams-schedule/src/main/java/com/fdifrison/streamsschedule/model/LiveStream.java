package com.fdifrison.streamsschedule.model;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public record LiveStream(String id, @NotEmpty String title, String description, String url, LocalDateTime startDate,
                         LocalDateTime endDate) {

    // under the hood a record class is similar to an immutable class, the main difference is that getters are
    // created with the same name of the components, therefore, to access a property we can simply do "record.component"
    // es. LiveStream.title



}
