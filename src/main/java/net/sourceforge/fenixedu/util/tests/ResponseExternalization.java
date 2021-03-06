package net.sourceforge.fenixedu.util.tests;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseExternalization {

    private static final Logger logger = LoggerFactory.getLogger(ResponseExternalization.class);

    public static String externalize(Response source) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(out);
        encoder.writeObject(source);
        encoder.close();
        try {
            // I think that this is wrong and that we should get the
            // bytes of the ByteArrayOutputStream interpreted as
            // UTF-8, which is what the XMLEncoder produces in the
            // first place.
            // WARNING: If this is changed, the internalize method
            // should be changed accordingly.
            return out.toString(Charset.defaultCharset().name());
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
            return out.toString();
        }
    }

    public static Response internalize(String source) {
        return getResponse(source);
    }

    private static Response getResponse(String xmlResponse) {
        XMLDecoder decoder = null;
        try {
            decoder = new XMLDecoder(new ByteArrayInputStream(xmlResponse.getBytes(CharEncoding.UTF_8)));
        } catch (UnsupportedEncodingException e1) {
            logger.error(e1.getMessage(), e1);
        }
        Response response = null;
        try {
            response = (Response) decoder.readObject();
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error(e.getMessage(), e);
        }
        decoder.close();
        return response;
    }
}
