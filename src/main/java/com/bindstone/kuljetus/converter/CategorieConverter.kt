package com.bindstone.kuljetus.converter

import com.bindstone.kuljetus.domain.enumeration.Categorie
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter

@ReadingConverter
class CategorieConverter : Converter<String, Categorie> {
    override fun convert(s: String): Categorie {
        return Categorie.byCode(s)
    }
}
