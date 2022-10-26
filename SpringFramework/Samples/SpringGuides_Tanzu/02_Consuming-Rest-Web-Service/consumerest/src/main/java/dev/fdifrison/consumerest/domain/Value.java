package dev.fdifrison.consumerest.domain;

public record Value(Long id, String quote) {

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
