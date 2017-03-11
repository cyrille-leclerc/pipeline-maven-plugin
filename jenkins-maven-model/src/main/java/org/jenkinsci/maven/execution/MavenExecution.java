package org.jenkinsci.maven.execution;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
@XmlRootElement(name = "maven-execution")
@XmlAccessorType(XmlAccessType.FIELD)
public class MavenExecution {

    @XmlElement
    public List<Object> events = new ArrayList<Object>();

    public void add(MavenEvent event) {
        this.events.add(event);
    }
}
