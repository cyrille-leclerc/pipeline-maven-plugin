package org.jenkinsci.maven.execution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
/*
    <plugin
        executionId="default-resources"
        goal="resources"
        groupId="org.apache.maven.plugins"
        artifactId="maven-resources-plugin"
        version="2.6">
      <buildFilters>${project.build.filters}</buildFilters>
      <encoding>${encoding}</encoding>
      <escapeString>${maven.resources.escapeString}</escapeString>
      <escapeWindowsPaths>${maven.resources.escapeWindowsPaths}</escapeWindowsPaths>
      <includeEmptyDirs>${maven.resources.includeEmptyDirs}</includeEmptyDirs>
      <outputDirectory>${project.build.outputDirectory}</outputDirectory>
      <overwrite>${maven.resources.overwrite}</overwrite>
      <project>${project}</project>
      <resources>${project.resources}</resources>
      <session>${session}</session>
      <supportMultiLineFiltering>${maven.resources.supportMultiLineFiltering}</supportMultiLineFiltering>
      <useBuildFilters>true</useBuildFilters>
      <useDefaultDelimiters>true</useDefaultDelimiters>
    </plugin>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Plugin {
    @XmlAttribute
    String executionId;
    @XmlAttribute
    String goal;
    @XmlAttribute
    String groupId;
    @XmlAttribute
    String artifactId;
    @XmlAttribute
    String version;

    @XmlElement(name = "property")
    List<Property> properties = new ArrayList<Property>();

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Properties {
        List<Property> properties;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Property {
        public Property() {
        }

        public Property(String name, String value) {
            this.name = name;
            this.value = value;
        }

        @XmlAttribute
        public String name;

        @XmlValue
        public String value;
    }
}
