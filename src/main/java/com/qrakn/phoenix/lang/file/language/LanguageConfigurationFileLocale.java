package com.qrakn.phoenix.lang.file.language;

public enum LanguageConfigurationFileLocale{
    ENGLISH("en"), 
    JAPANESE("ja"),
    EXPLICIT("ex"),
    FRENCH("fr"), 
    SPANISH("es"), 
    PORTUGUESE("po");
    
    private final String abbreviation;
    
    LanguageConfigurationFileLocale(final String abbreviation) {
        this.abbreviation = abbreviation;
    }
    
    public static LanguageConfigurationFileLocale getByAbbreviation(final String abbreviation) {
        for (final LanguageConfigurationFileLocale locale : values()) {
            if (locale.getAbbreviation().equalsIgnoreCase(abbreviation)) {
                return locale;
            }
        }
        return LanguageConfigurationFileLocale.ENGLISH;
    }
    
    public static LanguageConfigurationFileLocale getByName(final String name) {
        for (final LanguageConfigurationFileLocale locale : values()) {
            if (locale.getAbbreviation().equalsIgnoreCase(name)) {
                return locale;
            }
        }
        return LanguageConfigurationFileLocale.ENGLISH;
    }
    
    public String getAbbreviation() {
        return this.abbreviation;
    }
}
