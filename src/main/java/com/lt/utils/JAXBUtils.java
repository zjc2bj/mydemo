package com.lt.utils;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

public class JAXBUtils {

    @SuppressWarnings("unchecked")
    public static <T> T unmarshal(String xml, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Reader reader = new StringReader(xml);
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> String marshal(T t) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter out = new StringWriter();
            marshaller.marshal(t, new StreamResult(out));
            return out.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T unmarshal(String xml, Class<T> clazz, final boolean isIgnoreNamespace) throws SAXException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            /*
             * Reader reader = new StringReader(xml); return (T)
             * unmarshaller.unmarshal(reader);
             */
            XMLReader reader = XMLReaderFactory.createXMLReader();
            XMLFilterImpl nsfFilter = new XMLFilterImpl() {
                private boolean ignoreNamespace = isIgnoreNamespace;

                @Override
                public void startDocument() throws SAXException {
                    super.startDocument();
                }

                @Override
                public void startElement(String uri, String localName, String qName, Attributes atts)
                        throws SAXException {
                    if (this.ignoreNamespace)
                        uri = "";
                    super.startElement(uri, localName, qName, atts);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (this.ignoreNamespace)
                        uri = "";
                    super.endElement(uri, localName, localName);
                }

                @Override
                public void startPrefixMapping(String prefix, String url) throws SAXException {
                    if (!this.ignoreNamespace)
                        super.startPrefixMapping("", url);
                }
            };
            nsfFilter.setParent(reader);
            InputSource input = new InputSource(new StringReader(xml));
            SAXSource source = new SAXSource(nsfFilter, input);
            return (T) unmarshaller.unmarshal(source);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
