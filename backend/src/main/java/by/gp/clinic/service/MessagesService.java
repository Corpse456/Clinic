package by.gp.clinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final ResourceBundleMessageSource messageSourceAccessor;

    public String get(final Enum<?> enumElement) {
        return get(enumElement.getClass().getSimpleName() + "." + enumElement.name());
    }

    public String get(final String code) {
        return messageSourceAccessor.getMessage(code, null, Locale.getDefault());
    }
}
