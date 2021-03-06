package org.jenkinsci.plugins.pipeline.maven.util;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
public class XmlUtilsTest {
    private DocumentBuilder documentBuilder;

    @Before
    public void before() throws Exception {
        documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    @Test
    public void getUniqueChildElementOrNull_two_levels_children() throws Exception {
        String xml =
                "<a>" +
                        "   <b><c>my text</c></b>" +
                        "   <b2>b2</b2>" +
                        "</a>";
        Element documentElement = toXml(xml);
        Element actualElement = XmlUtils.getUniqueChildElementOrNull(documentElement, "b", "c");
        Assert.assertThat(actualElement.getTextContent(), CoreMatchers.is("my text"));
    }

    @Test
    public void getUniqueChildElementOrNull_return_null_on_first_level() throws Exception {
        String xml =
                "<a>" +
                        "   <b><c>my text</c></b>" +
                        "   <b2>b2</b2>" +
                        "</a>";
        Element documentElement = toXml(xml);
        Element actualElement = XmlUtils.getUniqueChildElementOrNull(documentElement, "does-npt-exist", "c");
        Assert.assertThat(actualElement, CoreMatchers.nullValue());
    }

    @Test
    public void getUniqueChildElementOrNull_return_null_on_second_level() throws Exception {
        String xml =
                "<a>" +
                        "   <b><c>my text</c></b>" +
                        "   <b2>b2</b2>" +
                        "</a>";
        Element documentElement = toXml(xml);
        Element actualElement = XmlUtils.getUniqueChildElementOrNull(documentElement, "b", "does-not-exist");
        Assert.assertThat(actualElement, CoreMatchers.nullValue());
    }

    @Test
    public void test_getUniqueChildElementOrNull_one_level_child() throws Exception {
        String xml =
                "<a>" +
                        "   <b><c>my text</c></b>" +
                        "   <b2>b2</b2>" +
                        "</a>";
        Element documentElement = toXml(xml);
        Element actualElement = XmlUtils.getUniqueChildElementOrNull(documentElement, "b");
        Assert.assertThat(actualElement.getTextContent(), CoreMatchers.is("my text"));
    }

    @Test
    public void test_getExecutionEvents_search_one_type() throws Exception {
        String xml =
                "<mavenExecution>" +
                        "<ExecutionEvent type='ProjectSucceeded' />" +
                        "</mavenExecution>";
        Element documentElement = toXml(xml);
        List<Element> actualElements = XmlUtils.getExecutionEvents(documentElement, "ProjectSucceeded");
        Assert.assertThat(actualElements.size(), CoreMatchers.is(1));
    }

    @Test
    public void test_getExecutionEvents_search_two_types() throws Exception {
        String xml =
                "<mavenExecution>" +
                        "<ExecutionEvent type='ProjectSucceeded' />" +
                        "</mavenExecution>";
        Element documentElement = toXml(xml);
        List<Element> actualElements = XmlUtils.getExecutionEvents(documentElement, "ProjectSucceeded", "ProjectFailed");
        Assert.assertThat(actualElements.size(), CoreMatchers.is(1));
    }

    @Test
    public void test_getExecutionEvents_return_empty_searching_one_type() throws Exception {
        String xml =
                "<mavenExecution>" +
                        "<ExecutionEvent type='ProjectSkipped' />" +
                        "</mavenExecution>";
        Element documentElement = toXml(xml);
        List<Element> actualElements = XmlUtils.getExecutionEvents(documentElement, "ProjectSucceeded");
        Assert.assertThat(actualElements.size(), CoreMatchers.is(0));
    }

    @Test
    public void test_getExecutionEvents_return_empty_searching_two_types() throws Exception {
        String xml =
                "<mavenExecution>" +
                        "<ExecutionEvent type='ProjectSkipped' />" +
                        "</mavenExecution>";
        Element documentElement = toXml(xml);
        List<Element> actualElements = XmlUtils.getExecutionEvents(documentElement, "ProjectSucceeded", "ProjectFailed");
        Assert.assertThat(actualElements.size(), CoreMatchers.is(0));
    }

    private Element toXml(String xml) throws SAXException, IOException {
        return documentBuilder.parse(new InputSource(new StringReader(xml))).getDocumentElement();
    }

    @Test
    public void concatenate_two_strings(){
        List<String> elements = Arrays.asList("a", "b", "c");
        String actual = XmlUtils.join(elements, ",");
        Assert.assertThat(actual, CoreMatchers.is("a,b,c"));
    }
}
