package org.jenkinsci.maven.execution;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
/*
    <project
    baseDir="/Users/cleclerc/git/jenkins/pipeline-maven-plugin/jenkins-maven-model"
    file="/Users/cleclerc/git/jenkins/pipeline-maven-plugin/jenkins-maven-model/pom.xml"
    groupId="org.jenkins-ci.plugins"
    name="Jenkins Pipeline Maven Plugin Model"
    artifactId="pipeline-maven-plugin-model"
    version="2.0-beta-7-SNAPSHOT">
      <build
      sourceDirectory=".../jenkins-maven-model/src/main/java"
      directory=".../pipeline-maven-plugin/jenkins-maven-model/target"/>
    </project>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Project {
    @XmlAttribute
    public String baseDir;
    @XmlAttribute
    public String file;
    @XmlAttribute
    public String groupId;
    @XmlAttribute
    public String artifactId;
    @XmlAttribute
    public String version;
    @XmlAttribute
    public String name;
    @XmlElement
    public Build build;

    @Override
    public String toString() {
        return "Project{" +
                "groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
