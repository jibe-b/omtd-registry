package eu.openminted.registry.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Created by stefania on 9/5/16.
 */
public class UsageInfo {

	
	@XmlElementWrapper(name = "foreseenUseInfos")
    @XmlElement(name = "foreseenUseInfo")
    private List<ForeseenUseInfo> foreseenUseInfos;
	
	@XmlElementWrapper(name = "actualUseInfos")
    @XmlElement(name = "actualUseInfo")
    private List<ActualUseInfo> actualUseInfos;

    public UsageInfo() {
    }

    public UsageInfo(List<ForeseenUseInfo> foreseenUseInfos, List<ActualUseInfo> actualUseInfos) {
        this.foreseenUseInfos = foreseenUseInfos;
        this.actualUseInfos = actualUseInfos;
    }

    public List<ForeseenUseInfo> getForeseenUseInfos() {
        return foreseenUseInfos;
    }

    public void setForeseenUseInfos(List<ForeseenUseInfo> foreseenUseInfos) {
        this.foreseenUseInfos = foreseenUseInfos;
    }

    public List<ActualUseInfo> getActualUseInfos() {
        return actualUseInfos;
    }

    public void setActualUseInfos(List<ActualUseInfo> actualUseInfos) {
        this.actualUseInfos = actualUseInfos;
    }
}
