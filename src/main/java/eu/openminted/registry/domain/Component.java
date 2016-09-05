package eu.openminted.registry.domain;

import java.util.List;

/**
 * Created by stefania on 9/5/16.
 */
public class Component {

    //required
    private MetadataHeaderInfo metadataHeaderInfo;
    //required
    private final String resourceType = "component";
    //required
    private ResourceIdentificationInfo resourceIdentificationInfo;
    //required
    private ContactInfo contactInfo;
    private VersionInfo versionInfo;
    private List<ValidationInfo> validationInfos;
    private UsageInfo usageInfo;
    private ResourceDocumentationInfo resourceDocumentationInfo;
    private ResourceCreationInfo resourceCreationInfo;
    //required
    //TODO this should be made into an enum (use componentType)
    private List<String> componentTypes;
    //required
    private List<ComponentDistributionInfo> distributionInfos;
    private ProcessingResourceInfo inputContentResourceInfo;
    private ProcessingResourceInfo outputResourceInfo;
    private ComponentDependencies componentDependencies;
    private ComponentCreationInfo componentCreationInfo;
    private ComponentEvaluationInfo componentEvaluationInfo;
    private ComponentDocumentationInfo componentDocumentationInfo;

    public Component() {
    }

    public Component(MetadataHeaderInfo metadataHeaderInfo, ResourceIdentificationInfo resourceIdentificationInfo,
                     ContactInfo contactInfo, List<String> componentTypes, List<ComponentDistributionInfo> distributionInfos) {
        this.metadataHeaderInfo = metadataHeaderInfo;
        this.resourceIdentificationInfo = resourceIdentificationInfo;
        this.contactInfo = contactInfo;
        this.componentTypes = componentTypes;
        this.distributionInfos = distributionInfos;
    }

    public Component(MetadataHeaderInfo metadataHeaderInfo, ResourceIdentificationInfo resourceIdentificationInfo,
                     ContactInfo contactInfo, VersionInfo versionInfo, List<ValidationInfo> validationInfos, UsageInfo usageInfo,
                     ResourceDocumentationInfo resourceDocumentationInfo, ResourceCreationInfo resourceCreationInfo,
                     List<String> componentTypes, List<ComponentDistributionInfo> distributionInfos,
                     ProcessingResourceInfo inputContentResourceInfo, ProcessingResourceInfo outputResourceInfo,
                     ComponentDependencies componentDependencies, ComponentCreationInfo componentCreationInfo,
                     ComponentEvaluationInfo componentEvaluationInfo, ComponentDocumentationInfo componentDocumentationInfo) {
        this.metadataHeaderInfo = metadataHeaderInfo;
        this.resourceIdentificationInfo = resourceIdentificationInfo;
        this.contactInfo = contactInfo;
        this.versionInfo = versionInfo;
        this.validationInfos = validationInfos;
        this.usageInfo = usageInfo;
        this.resourceDocumentationInfo = resourceDocumentationInfo;
        this.resourceCreationInfo = resourceCreationInfo;
        this.componentTypes = componentTypes;
        this.distributionInfos = distributionInfos;
        this.inputContentResourceInfo = inputContentResourceInfo;
        this.outputResourceInfo = outputResourceInfo;
        this.componentDependencies = componentDependencies;
        this.componentCreationInfo = componentCreationInfo;
        this.componentEvaluationInfo = componentEvaluationInfo;
        this.componentDocumentationInfo = componentDocumentationInfo;
    }

    public MetadataHeaderInfo getMetadataHeaderInfo() {
        return metadataHeaderInfo;
    }

    public void setMetadataHeaderInfo(MetadataHeaderInfo metadataHeaderInfo) {
        this.metadataHeaderInfo = metadataHeaderInfo;
    }

    public String getResourceType() {
        return resourceType;
    }

    public ResourceIdentificationInfo getResourceIdentificationInfo() {
        return resourceIdentificationInfo;
    }

    public void setResourceIdentificationInfo(ResourceIdentificationInfo resourceIdentificationInfo) {
        this.resourceIdentificationInfo = resourceIdentificationInfo;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public VersionInfo getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(VersionInfo versionInfo) {
        this.versionInfo = versionInfo;
    }

    public List<ValidationInfo> getValidationInfos() {
        return validationInfos;
    }

    public void setValidationInfos(List<ValidationInfo> validationInfos) {
        this.validationInfos = validationInfos;
    }

    public UsageInfo getUsageInfo() {
        return usageInfo;
    }

    public void setUsageInfo(UsageInfo usageInfo) {
        this.usageInfo = usageInfo;
    }

    public ResourceDocumentationInfo getResourceDocumentationInfo() {
        return resourceDocumentationInfo;
    }

    public void setResourceDocumentationInfo(ResourceDocumentationInfo resourceDocumentationInfo) {
        this.resourceDocumentationInfo = resourceDocumentationInfo;
    }

    public ResourceCreationInfo getResourceCreationInfo() {
        return resourceCreationInfo;
    }

    public void setResourceCreationInfo(ResourceCreationInfo resourceCreationInfo) {
        this.resourceCreationInfo = resourceCreationInfo;
    }

    public List<String> getComponentTypes() {
        return componentTypes;
    }

    public void setComponentTypes(List<String> componentTypes) {
        this.componentTypes = componentTypes;
    }

    public List<ComponentDistributionInfo> getDistributionInfos() {
        return distributionInfos;
    }

    public void setDistributionInfos(List<ComponentDistributionInfo> distributionInfos) {
        this.distributionInfos = distributionInfos;
    }

    public ProcessingResourceInfo getInputContentResourceInfo() {
        return inputContentResourceInfo;
    }

    public void setInputContentResourceInfo(ProcessingResourceInfo inputContentResourceInfo) {
        this.inputContentResourceInfo = inputContentResourceInfo;
    }

    public ProcessingResourceInfo getOutputResourceInfo() {
        return outputResourceInfo;
    }

    public void setOutputResourceInfo(ProcessingResourceInfo outputResourceInfo) {
        this.outputResourceInfo = outputResourceInfo;
    }

    public ComponentDependencies getComponentDependencies() {
        return componentDependencies;
    }

    public void setComponentDependencies(ComponentDependencies componentDependencies) {
        this.componentDependencies = componentDependencies;
    }

    public ComponentCreationInfo getComponentCreationInfo() {
        return componentCreationInfo;
    }

    public void setComponentCreationInfo(ComponentCreationInfo componentCreationInfo) {
        this.componentCreationInfo = componentCreationInfo;
    }

    public ComponentEvaluationInfo getComponentEvaluationInfo() {
        return componentEvaluationInfo;
    }

    public void setComponentEvaluationInfo(ComponentEvaluationInfo componentEvaluationInfo) {
        this.componentEvaluationInfo = componentEvaluationInfo;
    }

    public ComponentDocumentationInfo getComponentDocumentationInfo() {
        return componentDocumentationInfo;
    }

    public void setComponentDocumentationInfo(ComponentDocumentationInfo componentDocumentationInfo) {
        this.componentDocumentationInfo = componentDocumentationInfo;
    }
}
