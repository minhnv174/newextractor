package org.video.imusictube.extractor.exception;


public class ExtractionException extends Exception {
    public ExtractionException(String message) {
        super(message);
    }

    public ExtractionException(Throwable cause) {
        super(cause);
    }

    public ExtractionException(String message, Throwable cause) {
        super(message, cause);
    }
}