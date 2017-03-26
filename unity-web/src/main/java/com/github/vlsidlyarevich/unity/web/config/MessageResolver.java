package com.github.vlsidlyarevich.unity.web.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageResolver {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String msg) {
        return getMessage(msg, new Object[]{});
    }

    public String getMessage(String msg, Object... args) {
        return messageSource.getMessage(escapeMsg(msg), args, msg, LocaleContextHolder.getLocale());
    }

    public String getMessage(String msg, Object[] args, String defaultMsg) {
        return messageSource.getMessage(escapeMsg(msg), args, defaultMsg, LocaleContextHolder.getLocale());
    }

    private String escapeMsg(String msg) {
        if (StringUtils.isBlank(msg)) {
            return "";
        }
        return msg.replace(" ", "_");
    }
}