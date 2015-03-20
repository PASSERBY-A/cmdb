package com.hp.avmon.common.jackjson;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * json由日期字符串转换为日期对象时做的转换<br>
 * 
 * <pre>
 * &#064;JsonDeserialize(using = CustomDateTimeDeserializer.class)
 * public void setTime(Date time) {
 * 	this.time = time;
 * }
 * </pre>
 * 
 * @author chenxin
 * @date 2010-6-28 下午01:07:16
 */
public class CustomDateTimeDeserializer extends JsonDeserializer<Date> {
	private static final Log logger = LogFactory.getLog(CustomDateTimeDeserializer.class);
	private static final String[] DATE_TIME = { "yyyy-MM-dd HH:mm:ss" };

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext arg1) throws IOException, JsonProcessingException {
		try {
			return DateUtils.parseDate(parser.getText(), DATE_TIME);
		} catch (ParseException e) {
			logger.error("ParseException: ", e);
		}
		return null;
	}
}