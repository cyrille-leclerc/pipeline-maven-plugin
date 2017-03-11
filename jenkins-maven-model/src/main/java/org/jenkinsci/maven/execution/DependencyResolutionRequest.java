package org.jenkinsci.maven.execution;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
/*
  <DependencyResolutionRequest class="org.apache.maven.project.DefaultDependencyResolutionRequest" _time="2017-02-27 19:02:45.027">
    <project baseDir="/path/to/spring-petclinic" file="/path/to/spring-petclinic/pom.xml" groupId="org.springframework.samples" name="petclinic" artifactId="spring-petclinic" version="1.5.1">
      <build directory="/path/to/spring-petclinic/target"/>
    </project>
  </DependencyResolutionRequest>

 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DependencyResolutionRequest extends AbstractMavenEvent {
    @XmlElement
    public Project project;
}
