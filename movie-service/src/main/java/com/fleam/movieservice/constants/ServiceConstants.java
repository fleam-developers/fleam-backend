package com.fleam.movieservice.constants;

public class ServiceConstants {
    public static final String MOVIES_PATH = "/movies";
    public static final String CONTENT_FORMAT = ".mkv";

    public static final long CHUNK_SIZE = 64000; // 1 kb

    public static final String MOVIE_DATA = "moviesWithURL.csv";

    // http headers
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_VALUE = "video/x-matroska"; // x-matroska
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_RANGE = "Content-Range";
    public static final String ACCEPT_RANGES = "Accept-Ranges";
    public static final String BYTES = "bytes";
    public static final String KEEP_ALIVE = "Keep-Alive";
    public static final String KEEP_ALIVE_VALUE = "timeout=5";


}
