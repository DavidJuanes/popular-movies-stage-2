package com.davidjuanes.popular_movies.two.domain;

import java.io.Serializable;

/**
 * Created by David on 20/03/2017.
 */

public class YoutubeVideo implements Serializable {
    private static final String URL_PATTERN = "https://www.youtube.com/watch?v=%s";
    private static final String IMAGE_URL_PATTERN = "https://img.youtube.com/vi/%s/0.jpg";
    private String videoId;

    public YoutubeVideo(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoUrl() {
        return String.format(URL_PATTERN, videoId);
    }

    public String getVideoImageUrl() {
        return String.format(IMAGE_URL_PATTERN, videoId);
    }
}
