package eu.openminted.registry.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Created by stefania on 9/5/16.
 */
public class CreationInfo {

    @XmlElementWrapper(name = "originalSources")
    @XmlElement(name = "hasOriginalSource")
    private List<RelatedResource> originalSources;
    
    private ProcessMode creationMode;
    private String creationModeDetails;
    
    @XmlElementWrapper(name = "creationSwComponents")
    @XmlElement(name = "isCreatedBy")
    private List<RelatedResource> creationSwComponents;

    public CreationInfo() {
    }

    public CreationInfo(List<RelatedResource> originalSources, ProcessMode creationMode, String creationModeDetails,
                        List<RelatedResource> creationSwComponents) {
        this.originalSources = originalSources;
        this.creationMode = creationMode;
        this.creationModeDetails = creationModeDetails;
        this.creationSwComponents = creationSwComponents;
    }

    public List<RelatedResource> getOriginalSources() {
        return originalSources;
    }

    public void setOriginalSources(List<RelatedResource> originalSources) {
        this.originalSources = originalSources;
    }

    public ProcessMode getCreationMode() {
        return creationMode;
    }

    public void setCreationMode(ProcessMode creationMode) {
        this.creationMode = creationMode;
    }

    public String getCreationModeDetails() {
        return creationModeDetails;
    }

    public void setCreationModeDetails(String creationModeDetails) {
        this.creationModeDetails = creationModeDetails;
    }

    public List<RelatedResource> getCreationSwComponents() {
        return creationSwComponents;
    }

    public void setCreationSwComponents(List<RelatedResource> creationSwComponents) {
        this.creationSwComponents = creationSwComponents;
    }
}
