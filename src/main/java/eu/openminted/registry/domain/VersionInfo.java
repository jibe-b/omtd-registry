package eu.openminted.registry.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by stefania on 9/5/16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class VersionInfo {

    //required
    @XmlElement(name="version")
    private String version;
    @XmlElement(name = "revision")
    private String revision;
    @XmlElement(name = "lastDateUpdated")
    private DateType lastDateUpdated;
    @XmlElement(name="updateFrequency")
    private String updateFrequency;

    public VersionInfo() {
    }

    public VersionInfo(String version) {
        this.version = version;
    }

    public VersionInfo(String version, String revision, DateType lastDateUpdated, String updateFrequency) {
        this.version = version;
        this.revision = revision;
        this.lastDateUpdated = lastDateUpdated;
        this.updateFrequency = updateFrequency;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public DateType getLastDateUpdated() {
        return lastDateUpdated;
    }

    public void setLastDateUpdated(DateType lastDateUpdated) {
        this.lastDateUpdated = lastDateUpdated;
    }

    public String getUpdateFrequency() {
        return updateFrequency;
    }

    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }
}