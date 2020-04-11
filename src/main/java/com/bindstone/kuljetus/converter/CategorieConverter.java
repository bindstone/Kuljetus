package com.bindstone.kuljetus.converter;

import com.bindstone.kuljetus.domain.enumeration.Categorie;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class CategorieConverter implements Converter<String, Categorie> {

    @Override
    public Categorie convert(String s) {
        return Categorie.byCode(s);
    }
}
