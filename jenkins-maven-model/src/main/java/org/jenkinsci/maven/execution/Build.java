package org.jenkinsci.maven.execution;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
/*
 <build
    sourceDirectory="/Users/cleclerc/git/jenkins/pipeline-maven-plugin/jenkins-maven-model/src/main/java"
    directory="/Users/cleclerc/git/jenkins/pipeline-maven-plugin/jenkins-maven-model/target"/>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Build {
    @XmlAttribute
    public String sourceDirectory;
    @XmlAttribute
    public String directory;

}
