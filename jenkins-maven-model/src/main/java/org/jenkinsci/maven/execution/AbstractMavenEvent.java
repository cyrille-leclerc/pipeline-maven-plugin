package org.jenkinsci.maven.execution;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractMavenEvent implements MavenEvent {

    private final static DatatypeFactory DATATYPE_FACTORY;

    static {
        try {
            DATATYPE_FACTORY = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    @XmlAttribute(name = "class")
    public String clazz;
    @XmlAttribute(name = "_time")
    public XMLGregorianCalendar time;

    public void setTime(GregorianCalendar time) {
        this.time = DATATYPE_FACTORY.newXMLGregorianCalendar(time);
    }

    /**
     * @see DatatypeConverter#parseDateTime(String)
     */
    public void setTime(String time) {
        Calendar calendar = DatatypeConverter.parseDateTime(time);
        GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.getTimeZone());
        gregorianCalendar.setTime(calendar.getTime());
        setTime(gregorianCalendar);
    }

    @Override
    public String toString() {
        return "MavenEvent{" +
                "clazz='" + clazz + '\'' +
                '}';
    }
}
