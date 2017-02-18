package org.jenkinsci.plugins.pipeline.maven.execution;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xmlunit.builder.Input;

import java.io.StringReader;
import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;

import static org.junit.Assert.*;
import static org.xmlunit.matchers.CompareMatcher.*;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
public class MavenExecutionReportXmlBindingTest {


    private JAXBContext context;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    private ObjectFactory factory;
    private DocumentBuilder documentBuilder;

    @Before
    public void setUp() throws Exception {
        context = JAXBContext.newInstance(getClass().getPackage().getName());
        marshaller = context.createMarshaller();
        unmarshaller = context.createUnmarshaller();
        factory = new ObjectFactory();
        documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        XMLUnit.setIgnoreWhitespace(Boolean.TRUE);
        XMLUnit.setIgnoreAttributeOrder(Boolean.TRUE);



    }

    /*

 */
    @Test
    public void testMavenExecutionRequest() throws Exception {
        System.err.println(DatatypeConverter.printDateTime(Calendar.getInstance()));

        final MavenExecutionRequest merFromJava = factory.createMavenExecutionRequest();
        merFromJava.clazz = "org.apache.maven.execution.DefaultMavenExecutionRequest";
        merFromJava.time = DatatypeConverter.parseDateTime("2017-02-19T00:24:59.705+01:00");
        merFromJava.pom = "/Users/cleclerc/git/jenkins/pipeline-maven-plugin/pom.xml";
        merFromJava.globalSettingsFile = "/usr/local/Cellar/maven/3.3.9/libexec/conf/settings.xml";
        merFromJava.userSettingsFile = "/Users/cleclerc/.m2/settings.xml";
        merFromJava.baseDirectory = "/Users/cleclerc/git/jenkins/pipeline-maven-plugin";

        Document actualFromJava = documentBuilder.newDocument();
        marshaller.marshal(merFromJava, actualFromJava);

        String expected = "<mavenExecutionRequest class='org.apache.maven.execution.DefaultMavenExecutionRequest' time='2017-02-19T00:24:59.705+01:00'>\n" +
                "    <pom>/Users/cleclerc/git/jenkins/pipeline-maven-plugin/pom.xml</pom>\n" +
                "    <globalSettingsFile>/usr/local/Cellar/maven/3.3.9/libexec/conf/settings.xml</globalSettingsFile>\n" +
                "    <userSettingsFile>/Users/cleclerc/.m2/settings.xml</userSettingsFile>\n" +
                "    <baseDirectory>/Users/cleclerc/git/jenkins/pipeline-maven-plugin</baseDirectory>\n" +
                "  </mavenExecutionRequest>";


        Source expectedAsSource = Input.fromString(expected).build();
        MavenExecutionRequest merFromXml = (MavenExecutionRequest) unmarshaller.unmarshal(new StringReader(expected));

        assertThat(actualFromJava.getDocumentElement(), isSimilarTo(expectedAsSource));
    }
}
