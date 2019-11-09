package com.hanyang.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonObjectMapper extends ObjectMapper {

	private static final Logger logger = LoggerFactory.getLogger(JacksonObjectMapper.class);
	private static final long serialVersionUID = -68945176830351139L;
	private static ObjectMapper staticObjectMapper;

	static {
		staticObjectMapper = new JacksonObjectMapper();
	}
	
	@SuppressWarnings("deprecation")
	public JacksonObjectMapper(){
		
		//final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		this
		.configure(SerializationFeature.INDENT_OUTPUT, true)
		.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true)
		.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		.setSerializationInclusion(JsonInclude.Include.NON_NULL)
		.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //.setDateFormat(df);
	}
	
	public static String toJson(Object entity){
		try {
			return staticObjectMapper.writeValueAsString(entity);
		}
		catch (JsonProcessingException e) {
			logger.error(e.getMessage(),e);
		}

		return null;
	}

}
