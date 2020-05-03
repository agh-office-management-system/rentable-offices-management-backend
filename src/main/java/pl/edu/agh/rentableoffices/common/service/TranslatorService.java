package pl.edu.agh.rentableoffices.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class TranslatorService {
    private static ResourceBundleMessageSource messageSource;

    public String translate(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public String translate(String code, Object[] params) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, params, locale);
    }
}
