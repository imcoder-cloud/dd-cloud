package fun.imcoder.cloud.auth.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @Description: 序列化异常类
 * @Author: cdd
 */
@Slf4j
public class DdOAuth2ExceptionSerializer extends StdSerializer<DdOAuth2Exception> {

    protected DdOAuth2ExceptionSerializer() {
        super(DdOAuth2Exception.class);
    }
    @Override
    public void serialize(DdOAuth2Exception e, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        log.error(e.getSummary());
        String msg = e.getSummary().replaceAll("\"","").replaceAll(" ","");
        Map<String,String> msgMap = Splitter.on(",").withKeyValueSeparator("=").split(msg);
        generator.writeStartObject();
        generator.writeObjectField("code", e.getHttpErrorCode());
        generator.writeObjectField("error", msgMap.get("error"));
        generator.writeStringField("message", msgMap.get("error_description"));
        if (e.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : e.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                generator.writeStringField(key, add);
            }
        }
        generator.writeEndObject();
    }
}
