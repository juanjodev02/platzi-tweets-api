package com.ada.platzi_tweets.api.models.converters

import com.ada.platzi_tweets.api.models.Location
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class LocationConverter : AttributeConverter<Location, String> {

    private val objectMapper = ObjectMapper()

    override fun convertToDatabaseColumn(attribute: Location?): String? {
        return if (attribute == null) null else objectMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): Location? {
        return if (dbData == null) null else objectMapper.readValue(dbData, Location::class.java)
    }
}