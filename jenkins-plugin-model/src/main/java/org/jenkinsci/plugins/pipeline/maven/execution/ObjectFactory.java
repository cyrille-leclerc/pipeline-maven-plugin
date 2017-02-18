package org.jenkinsci.plugins.pipeline.maven.execution;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.jenkinsci.plugins.pipeline.maven.execution package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.jenkinsci.plugins.pipeline.maven.execution
     */
    public ObjectFactory() {
    }

    MavenExecutionRequest createMavenExecutionRequest() {
        return new MavenExecutionRequest();
    }

}
