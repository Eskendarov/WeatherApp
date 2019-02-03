package ru.eskendarov.weatherapp;

/**
 * @author Enver Eskendarov
 * @date 02/02/2019
 */

final class Cities {

    private final String name;
    private final String country;
    private final int photoId;

    Cities(final String name, final String country, final int photoId) {
        this.name = name;
        this.country = country;
        this.photoId = photoId;
    }

    String getName() {
        return name;
    }

    String getCountry() {
        return country;
    }

    int getPhotoId() {
        return photoId;
    }

}