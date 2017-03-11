package org.jenkinsci.maven.execution;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
/*
  <DependencyResolutionResult class="org.apache.maven.project.DefaultDependencyResolutionResult" _time="2017-02-27 19:02:45.543"/>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DependencyResolutionResult extends AbstractMavenEvent {
}
