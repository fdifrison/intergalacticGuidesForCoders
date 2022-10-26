package dev.fdifrison.consumerest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
// ignores any property not bounded to the Json, meaning that is named exactly as the JSON is returned from API
public record Quote(String type, Value value) {

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
