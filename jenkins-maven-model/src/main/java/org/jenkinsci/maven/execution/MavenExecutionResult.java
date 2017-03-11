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
  <MavenExecutionResult class="org.apache.maven.execution.DefaultMavenExecutionResult" _time="2017-02-27 19:03:44.367">
    <buildSummary baseDir="/path/to/spring-petclinic" file="/path/to/spring-petclinic/pom.xml" groupId="org.springframework.samples" name="petclinic" artifactId="spring-petclinic" time="58958" version="1.5.1" class="org.apache.maven.execution.BuildSuccess">
      <build directory="/path/to/spring-petclinic/target"/>
    </buildSummary>
  </MavenExecutionResult>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MavenExecutionResult extends AbstractMavenEvent {
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class BuildSummary {
        @XmlElement
        public Build build;
    }
}
