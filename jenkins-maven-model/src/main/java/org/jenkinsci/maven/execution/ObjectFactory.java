package org.jenkinsci.maven.execution;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.jenkinsci.maven.execution package.
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

    public DefaultSettingsBuildingRequest createDefaultSettingsBuildingRequest() {
        return new DefaultSettingsBuildingRequest();
    }

    public DependencyResolutionRequest createDependencyResolutionRequest() {
        return new DependencyResolutionRequest();
    }

    public DependencyResolutionResult createDependencyResolutionResult() {
        return new DependencyResolutionResult();
    }

    public ExecutionEvent createExecutionEvent() {
        return new ExecutionEvent();
    }

    public MavenExecution createMavenExecution() {
        return new MavenExecution();
    }

    public MavenExecutionRequest createMavenExecutionRequest() {
        return new MavenExecutionRequest();
    }

    public MavenExecutionResult createMavenExecutionResult() {
        return new MavenExecutionResult();
    }

    public Build createBuild() {
        return new Build();
    }

    public Plugin createPlugin() {
        return new Plugin();
    }

    public Project createProject() {
        return new Project();
    }
}
