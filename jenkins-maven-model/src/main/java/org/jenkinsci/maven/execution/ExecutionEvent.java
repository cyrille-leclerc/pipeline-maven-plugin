package org.jenkinsci.maven.execution;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
/*
  <ExecutionEvent
    type="MojoStarted"
    class="org.apache.maven.lifecycle.internal.DefaultExecutionEvent"
    _time="2017-02-27 19:02:45.546">
    <project baseDir="/path/to/spring-petclinic" file="/path/to/spring-petclinic/pom.xml" groupId="org.springframework.samples" name="petclinic" artifactId="spring-petclinic" version="1.5.1">
      <build directory="/path/to/spring-petclinic/target"/>
    </project>
    <plugin executionId="default-clean" goal="clean" groupId="org.apache.maven.plugins" artifactId="maven-clean-plugin" version="2.6.1">
      <directory>${project.build.directory}</directory>
      <excludeDefaultDirectories>${clean.excludeDefaultDirectories}</excludeDefaultDirectories>
      <failOnError>${maven.clean.failOnError}</failOnError>
      <followSymLinks>${clean.followSymLinks}</followSymLinks>
      <outputDirectory>${project.build.outputDirectory}</outputDirectory>
      <reportDirectory>${project.build.outputDirectory}</reportDirectory>
      <retryOnError>${maven.clean.retryOnError}</retryOnError>
      <skip>${clean.skip}</skip>
      <testOutputDirectory>${project.build.testOutputDirectory}</testOutputDirectory>
      <verbose>${clean.verbose}</verbose>
    </plugin>
  </ExecutionEvent>

  <ExecutionEvent type="ProjectSucceeded" class="org.apache.maven.lifecycle.internal.DefaultExecutionEvent" _time="2017-02-27 19:03:43.966">
    <project baseDir="/path/to/spring-petclinic" file="/path/to/spring-petclinic/pom.xml" groupId="org.springframework.samples" name="petclinic" artifactId="spring-petclinic" version="1.5.1">
      <build directory="/path/to/spring-petclinic/target"/>
    </project>
    <no-execution-found/>
    <artifact extension="jar" groupId="org.springframework.samples" artifactId="spring-petclinic" id="org.springframework.samples:spring-petclinic:jar:1.5.1" type="jar" version="1.5.1">
      <file>/path/to/spring-petclinic/target/spring-petclinic-1.5.1.jar</file>
    </artifact>
    <attachedArtifacts>
      <artifact extension="jar" groupId="org.springframework.samples" classifier="sources" artifactId="spring-petclinic" id="org.springframework.samples:spring-petclinic:java-source:sources:1.5.1" type="java-source" version="1.5.1">
        <file>/path/to/spring-petclinic/target/spring-petclinic-1.5.1-sources.jar</file>
      </artifact>
    </attachedArtifacts>
  </ExecutionEvent>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExecutionEvent extends AbstractMavenEvent {
    enum Type {
        ProjectDiscoveryStarted,
        SessionStarted,
        SessionEnded,
        ProjectSkipped,
        ProjectStarted,
        ProjectSucceeded,
        ProjectFailed,
        MojoSkipped,
        MojoStarted,
        MojoSucceeded,
        MojoFailed,
        ForkStarted,
        ForkSucceeded,
        ForkFailed,
        ForkedProjectStarted,
        ForkedProjectSucceeded,
        ForkedProjectFailed,
    }

    @XmlAttribute
    public Type type;
    @XmlElement
    public Project project;
    @XmlElement
    public Plugin plugin;

    @XmlElement
    public Artifact artifact;

    @XmlElement
    public AttachedArtifacts attachedArtifacts = new AttachedArtifacts();

    @XmlAccessorType(XmlAccessType.FIELD)
    static class AttachedArtifacts {
        @XmlElement(name="artifact")
        public List<Artifact> artifacts = new ArrayList<Artifact>();
    }
    /*
    <artifact extension="jar" groupId="org.springframework.samples" artifactId="spring-petclinic" id="org.springframework.samples:spring-petclinic:jar:1.5.1" type="jar" version="1.5.1">
      <file>/path/to/spring-petclinic/target/spring-petclinic-1.5.1.jar</file>
    </artifact>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Artifact {
        @XmlAttribute
        public String extension;
        @XmlAttribute
        public String groupId;
        @XmlAttribute
        public String artifactId;

        @XmlAttribute
        public String version;

        @XmlAttribute
        public String type;

        @XmlAttribute
        public String classifier;

        @XmlElement
        public String file;


    }
}
