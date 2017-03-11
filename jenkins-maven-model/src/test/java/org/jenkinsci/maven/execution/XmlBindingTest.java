package org.jenkinsci.maven.execution;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xmlunit.builder.Input;

import java.io.StringReader;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static org.junit.Assert.*;
import static org.xmlunit.matchers.CompareMatcher.*;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
public class XmlBindingTest {


    private JAXBContext context;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    private ObjectFactory factory;
    private DocumentBuilder documentBuilder;

    @Before
    public void setUp() throws Exception {
        context = JAXBContext.newInstance(getClass().getPackage().getName());
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        unmarshaller = context.createUnmarshaller();
        factory = new ObjectFactory();
        documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // XMLUnit.setIgnoreWhitespace(Boolean.TRUE);
        // XMLUnit.setIgnoreAttributeOrder(Boolean.TRUE);
    }

    @Test
    public void test_MavenExecutionRequest_java_to_xml() throws Exception {

        final MavenExecutionRequest merFromJava = factory.createMavenExecutionRequest();
        merFromJava.clazz = "org.apache.maven.execution.DefaultMavenExecutionRequest";
        merFromJava.setTime("2017-02-19T00:24:59.705+01:00");
        merFromJava.pom = "/Users/cleclerc/git/jenkins/pipeline-maven-plugin/pom.xml";
        merFromJava.globalSettingsFile = "/usr/local/Cellar/maven/3.3.9/libexec/conf/settings.xml";
        merFromJava.userSettingsFile = "/Users/cleclerc/.m2/settings.xml";
        merFromJava.baseDirectory = "/Users/cleclerc/git/jenkins/pipeline-maven-plugin";

        Document actualFromJava = documentBuilder.newDocument();
        marshaller.marshal(merFromJava, actualFromJava);

        String expected = "<mavenExecutionRequest clazz=\"org.apache.maven.execution.DefaultMavenExecutionRequest\" time=\"2017-02-19T00:24:59.705+01:00\">" +
                "<pom>/Users/cleclerc/git/jenkins/pipeline-maven-plugin/pom.xml</pom>" +
                "<userSettingsFile>/Users/cleclerc/.m2/settings.xml</userSettingsFile>" +
                "<globalSettingsFile>/usr/local/Cellar/maven/3.3.9/libexec/conf/settings.xml</globalSettingsFile>" +
                "<baseDirectory>/Users/cleclerc/git/jenkins/pipeline-maven-plugin</baseDirectory>" +
                "</mavenExecutionRequest>";


        Source expectedAsSource = Input.fromString(expected).build();

        MavenExecutionRequest merFromXml = (MavenExecutionRequest) unmarshaller.unmarshal(new StringReader(expected));


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        System.out.println();
        System.out.println("XML FROM JAVA");
        transformer.transform(new DOMSource(actualFromJava), new StreamResult(System.out));
        System.out.println();
        System.out.println("XML FROM XML");
        transformer.transform(expectedAsSource, new StreamResult(System.out));
        System.out.println();

        assertThat(actualFromJava.getDocumentElement(), isSimilarTo(expectedAsSource));
    }


    @Test
    public void test_MavenExecutionRequest_xml_to_java() throws Exception {
        String source = "<mavenExecutionRequest clazz=\"org.apache.maven.execution.DefaultMavenExecutionRequest\" time=\"2017-02-19T00:24:59.705+01:00\">" +
                "<pom>/Users/cleclerc/git/jenkins/pipeline-maven-plugin/pom.xml</pom>" +
                "<userSettingsFile>/Users/cleclerc/.m2/settings.xml</userSettingsFile>" +
                "<globalSettingsFile>/usr/local/Cellar/maven/3.3.9/libexec/conf/settings.xml</globalSettingsFile>" +
                "<baseDirectory>/Users/cleclerc/git/jenkins/pipeline-maven-plugin</baseDirectory>" +
                "</mavenExecutionRequest>";
        MavenExecutionRequest mavenExecutionRequest = (MavenExecutionRequest) unmarshaller.unmarshal(new StringReader(source));
        System.out.println(mavenExecutionRequest);

    }

    @Test
    public void test_Project_xml_to_java() throws Exception {
        String source = "    <project\n" +
                "    baseDir='/Users/cleclerc/git/jenkins/pipeline-maven-plugin/jenkins-maven-model'\n" +
                "    file='/Users/cleclerc/git/jenkins/pipeline-maven-plugin/jenkins-maven-model/pom.xml'\n" +
                "    groupId='org.jenkins-ci.plugins'\n" +
                "    name='Jenkins Pipeline Maven Plugin Model'\n" +
                "    artifactId='pipeline-maven-plugin-model'\n" +
                "    version='2.0-beta-7-SNAPSHOT'>\n" +
                "      <build sourceDirectory='/Users/cleclerc/git/jenkins/pipeline-maven-plugin/jenkins-maven-model/src/main/java' directory='/Users/cleclerc/git/jenkins/pipeline-maven-plugin/jenkins-maven-model/target'/>\n" +
                "    </project>\n";

        Project project = (Project) unmarshaller.unmarshal(new StringReader(source));
        System.out.println(project);
    }

    @Test
    public void test_Project_java_to_xml() throws Exception {

        Project project = new Project();
        project.groupId = "org.jenkins-ci.plugins";
        project.artifactId = "pipeline-maven-plugin-model";
        project.version = "2.0-beta-7-SNAPSHOT";

        Build build = new Build();
        project.build = build;
        build.directory = "pipeline-maven-plugin/pipeline-maven-plugin-model/target";
        build.sourceDirectory = "pipeline-maven-plugin/pipeline-maven-plugin-model/src/main/java";

        System.out.println(project);

        marshaller.marshal(project, System.out);

        String source = "    <project\n" +
                "    baseDir='/Users/cleclerc/git/jenkins/pipeline-maven-plugin/jenkins-maven-model'\n" +
                "    file='/Users/cleclerc/git/jenkins/pipeline-maven-plugin/jenkins-maven-model/pom.xml'\n" +
                "    groupId='org.jenkins-ci.plugins'\n" +
                "    name='Jenkins Pipeline Maven Plugin Model'\n" +
                "    artifactId='pipeline-maven-plugin-model'\n" +
                "    version='2.0-beta-7-SNAPSHOT'>\n" +
                "      <build sourceDirectory='/Users/cleclerc/git/jenkins/pipeline-maven-plugin/jenkins-maven-model/src/main/java' directory='/Users/cleclerc/git/jenkins/pipeline-maven-plugin/jenkins-maven-model/target'/>\n" +
                "    </project>\n";

    }

    @Test
    public void test_Plugin_java_to_xml() throws Exception {
        Plugin plugin = new Plugin();
        plugin.groupId = "org.apache.maven.plugins";
        plugin.artifactId = "maven-resources-plugin";
        plugin.version = "2.6";
        plugin.goal = "resources";
        plugin.executionId = "default-resource";

        plugin.properties.add(new Plugin.Property("buildFilters", "${project.build.filters}"));
        plugin.properties.add(new Plugin.Property("encoding", "${encoding}"));

        marshaller.marshal(plugin, System.out);


        String xml = "    <plugin executionId='default-resources' goal='resources' groupId='org.apache.maven.plugins' artifactId='maven-resources-plugin' version='2.6'>\n" +
                "      <buildFilters>${project.build.filters}</buildFilters>\n" +
                "      <encoding>${encoding}</encoding>\n" +
                "      <escapeString>${maven.resources.escapeString}</escapeString>\n" +
                "      <escapeWindowsPaths>${maven.resources.escapeWindowsPaths}</escapeWindowsPaths>\n" +
                "      <includeEmptyDirs>${maven.resources.includeEmptyDirs}</includeEmptyDirs>\n" +
                "      <outputDirectory>${project.build.outputDirectory}</outputDirectory>\n" +
                "      <overwrite>${maven.resources.overwrite}</overwrite>\n" +
                "      <project>${project}</project>\n" +
                "      <resources>${project.resources}</resources>\n" +
                "      <session>${session}</session>\n" +
                "      <supportMultiLineFiltering>${maven.resources.supportMultiLineFiltering}</supportMultiLineFiltering>\n" +
                "      <useBuildFilters>true</useBuildFilters>\n" +
                "      <useDefaultDelimiters>true</useDefaultDelimiters>\n" +
                "    </plugin>";
    }

    @Test
    public void test_ExecutionEvent_ProjectSucceeded_java_to_xml() throws Exception {
        ExecutionEvent event = new ExecutionEvent();
        event.type = ExecutionEvent.Type.ProjectSucceeded;
        event.clazz = "org.apache.maven.lifecycle.internal.DefaultExecutionEvent";

        Project project = new Project();
        event.project = project;

        project.groupId = "org.springframework.samples";
        project.artifactId = "spring-petclinic";
        project.version = "1.5.1";
        project.name = "petclinic";
        project.file = "/path/to/spring-petclinic/pom.xml";
        project.baseDir = "/path/to/spring-petclinic";

        Build build = new Build();
        project.build = build;
        build.directory = "/path/to/spring-petclinic/target";
        build.sourceDirectory = "/path/to/spring-petclinic/src/main/java";

        ExecutionEvent.Artifact artifact = new ExecutionEvent.Artifact();
        event.artifact = artifact;
        artifact.groupId = "org.springframework.samples";
        artifact.artifactId = "spring-petclinic";
        artifact.version = "1.5.1";
        artifact.extension = "jar";
        artifact.type = "jar";
        artifact.file = "/path/to/spring-petclinic/target/spring-petclinic-1.5.1.jar";

        ExecutionEvent.Artifact attachedArtifact = new ExecutionEvent.Artifact();
        event.attachedArtifacts.artifacts.add(attachedArtifact);
        attachedArtifact.groupId = "org.springframework.samples";
        attachedArtifact.artifactId = "spring-petclinic";
        attachedArtifact.version = "1.5.1";
        attachedArtifact.classifier = "sources";
        attachedArtifact.extension = "jar";
        attachedArtifact.type = "java-source";
        attachedArtifact.file = "/path/to/spring-petclinic/target/spring-petclinic-1.5.1-sources.jar";

        marshaller.marshal(event, System.out);


        String xml = "  <ExecutionEvent type='ProjectSucceeded' class='org.apache.maven.lifecycle.internal.DefaultExecutionEvent' _time='2017-02-27 19:03:43.966'>\n" +
                "    <project baseDir='/path/to/spring-petclinic' file='/path/to/spring-petclinic/pom.xml' groupId='org.springframework.samples' name='petclinic' artifactId='spring-petclinic' version='1.5.1'>\n" +
                "      <build directory='/path/to/spring-petclinic/target'/>\n" +
                "    </project>\n" +
                "    <no-execution-found/>\n" +
                "    <artifact extension='jar' groupId='org.springframework.samples' artifactId='spring-petclinic' id='org.springframework.samples:spring-petclinic:jar:1.5.1' type='jar' version='1.5.1'>\n" +
                "      <file>/path/to/spring-petclinic/target/spring-petclinic-1.5.1.jar</file>\n" +
                "    </artifact>\n" +
                "    <attachedArtifacts>\n" +
                "      <artifact extension='jar' groupId='org.springframework.samples' classifier='sources' artifactId='spring-petclinic' id='org.springframework.samples:spring-petclinic:java-source:sources:1.5.1' type='java-source' version='1.5.1'>\n" +
                "        <file>/path/to/spring-petclinic/target/spring-petclinic-1.5.1-sources.jar</file>\n" +
                "      </artifact>\n" +
                "    </attachedArtifacts>\n" +
                "  </ExecutionEvent>";
    }

    @Test
    public void test_MavenExecution_java_to_xml() throws Exception {
        MavenExecution execution = new MavenExecution();

        execution.events.add(new MavenExecutionRequest());
        execution.events.add(new MavenExecutionResult());

        marshaller.marshal(execution, System.out);

    }
}
