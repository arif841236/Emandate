package com.mandate.model.common;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateTimeConverter extends TypeAdapter <LocalDateTime> {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	public void write(JsonWriter out, LocalDateTime date) throws IOException {
		if (date == null) {
			out.nullValue();
		} else {
			out.value(formatter.format(date));
		}
	}

	@Override
	public LocalDateTime read(JsonReader in) throws IOException {
		if(in.peek()==null) {
			in.nextNull();
			return null;
		}
		else {
			String date = in.nextString();
			return LocalDateTime.parse(date, formatter);
		}
	}
}

