package com.ada.platzi_tweets.api.models.converters

import com.ada.platzi_tweets.api.models.Author
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class AuthorConverter : AttributeConverter<Author, String> {

    private val objectMapper = ObjectMapper()

    override fun convertToDatabaseColumn(attribute: Author?): String? {
        return if (attribute == null) null else objectMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): Author? {
        return if (dbData == null) null else objectMapper.readValue(dbData, Author::class.java)
    }
}