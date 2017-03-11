package org.jenkinsci.maven.execution;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
/*
  <DefaultSettingsBuildingRequest
        class="org.apache.maven.settings.building.DefaultSettingsBuildingRequest"
        _time="2017-02-27 19:02:44.206">
    <userSettingsFile>/Users/cleclerc/.m2/settings.xml</userSettingsFile>
    <globalSettings>/usr/local/Cellar/maven/3.3.9/libexec/conf/settings.xml</globalSettings>
  </DefaultSettingsBuildingRequest>

 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DefaultSettingsBuildingRequest extends AbstractMavenEvent {

    @XmlElement
    String userSettingsFile;

    @XmlElement
    String globalSettings;

    @Override
    public String toString() {
        return "DefaultSettingsBuildingRequest{" +
                "userSettingsFile='" + userSettingsFile + '\'' +
                ", globalSettings='" + globalSettings + '\'' +
                '}';
    }
}
