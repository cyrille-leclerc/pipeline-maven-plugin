package org.jenkinsci.maven.execution;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
/*
<MavenExecutionRequest class="org.apache.maven.execution.DefaultMavenExecutionRequest" _time="2017-02-18 00:01:21.533">
    <pom>/Users/cleclerc/git/jenkins/pipeline-maven-plugin/pom.xml</pom>
    <globalSettingsFile>/usr/local/Cellar/maven/3.3.9/libexec/conf/settings.xml</globalSettingsFile>
    <userSettingsFile>/Users/cleclerc/.m2/settings.xml</userSettingsFile>
    <baseDirectory>/Users/cleclerc/git/jenkins/pipeline-maven-plugin</baseDirectory>
  </MavenExecutionRequest>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class MavenExecutionRequest extends AbstractMavenEvent {
    @XmlElement
    String pom;
    @XmlElement
    String userSettingsFile;
    @XmlElement
    String globalSettingsFile;
    @XmlElement
    String baseDirectory;
}

