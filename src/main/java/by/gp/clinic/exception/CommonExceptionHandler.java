package by.gp.clinic.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionHandler {

    //Common exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleCommonException(final Exception ex) {
        ex.printStackTrace();

        final StringBuilder stringBuilder = new StringBuilder();
        appendException(stringBuilder, ex);

        return commonExceptionResponse(stringBuilder.toString());
    }

    private void appendException(final StringBuilder stringBuilder, final Throwable ex) {
        final StackTraceElement[] stackTrace = ex.getStackTrace();

        final int max = 10;
        final int limit = stackTrace.length > max ? stackTrace.length : max;

        stringBuilder.append(ex.toString());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());

        Arrays.stream(stackTrace)
            .limit(limit)
            .forEach(stackTraceElement -> {
                stringBuilder.append(stackTraceElement.toString());
                stringBuilder.append(System.lineSeparator());
            });

        if (ex.getCause() != null) {
            stringBuilder
                .append("======================================= CAUSED BY =======================================");
            stringBuilder.append(System.lineSeparator());
            appendException(stringBuilder, ex.getCause());
        }
    }

    private ResponseEntity<String> commonExceptionResponse(final String text) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("{\"======================================= MESSAGE =======================================\":" +
                  System.lineSeparator() + "\"" + text + "\"}");

    }
    //endregion
}
