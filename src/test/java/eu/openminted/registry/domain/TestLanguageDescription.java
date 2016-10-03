package eu.openminted.registry.domain;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xmlunit.builder.Input;
import org.xmlunit.validation.Languages;
import org.xmlunit.validation.ValidationProblem;
import org.xmlunit.validation.ValidationResult;
import org.xmlunit.validation.Validator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestLanguageDescription {

	private static Marshaller marshaller;
	private static Unmarshaller unmarshaller;

	@BeforeClass
	public static void init() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);

		marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		unmarshaller = jc.createUnmarshaller();
	}

	@Test
	public void serializeAllFields() throws JAXBException {
		LanguageDescription LanguageDescriptionResource = (LanguageDescription) unmarshaller.unmarshal(new StringReader(LanguageDescriptionResourceXml));

		System.out.println("Output:");
		marshaller.marshal(LanguageDescriptionResource, System.out);
		System.out.println("EOF XML");
		StringWriter sw = new StringWriter();
		marshaller.marshal(LanguageDescriptionResource, sw);
		
		Assert.assertEquals(true, isValidLanguageDescriptionResourceXml(sw.toString()));
	}
	
	@Test
	public void serializeAlmostAllFields() throws JAXBException {
		LanguageDescription LanguageDescriptionResource = (LanguageDescription) unmarshaller.unmarshal(new StringReader(almostAllLanguageDescriptionResourceXml));

		//System.out.println("Output:");
		//marshaller.marshal(LanguageDescriptionResource, System.out);
		//System.out.println("EOF XML");
		StringWriter sw = new StringWriter();
		marshaller.marshal(LanguageDescriptionResource, sw);
		
		Assert.assertEquals(true, isValidLanguageDescriptionResourceXml(sw.toString()));
	}

	@Test
	public void compare1() throws JAXBException {
		LanguageDescription LanguageDescriptionResource = createLanguageDescription();

		StringWriter sw = new StringWriter();
		marshaller.marshal(LanguageDescriptionResource, sw);

		LanguageDescription c2 = (LanguageDescription) unmarshaller.unmarshal(new StringReader(sw.toString()));
		StringWriter sw2 = new StringWriter();
		marshaller.marshal(c2, sw2);

		Assert.assertEquals(sw.toString(), sw2.toString());
	}

	@Test
	public void testUnmarshal() throws JAXBException {
		LanguageDescription LanguageDescriptionResource = (LanguageDescription) unmarshaller.unmarshal(new StringReader(LanguageDescriptionResourceXml));

	}

	@Test
	public void compare2() throws JAXBException {
		LanguageDescription LanguageDescriptionResource = (LanguageDescription) unmarshaller.unmarshal(new StringReader(LanguageDescriptionResourceXml));
		StringWriter sw = new StringWriter();

		marshaller.marshal(LanguageDescriptionResource, sw);
		
		System.out.println("compare2");
		System.out.println(sw.toString());
		System.out.println("EOF");
		
		//Assert.assertEquals(LanguageDescriptionResourceXml, sw.toString());
	}

	@Test
	public void testJson() throws IOException, JAXBException {
		LanguageDescription LanguageDescriptionResource = createLanguageDescription();

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		StringWriter sw = new StringWriter();

		mapper.writeValue(sw, LanguageDescriptionResource);

		System.out.println(sw.toString());
	}

	private boolean isValidLanguageDescriptionResourceXml(String xml) {
		Validator validator = Validator.forLanguage(Languages.W3C_XML_SCHEMA_NS_URI);

		validator.setSchemaSource(Input.fromURI("http://194.177.192.223/openminted/schema/OMTD-SHARE-LanguageDescription.xsd").build());
		ValidationResult result = validator.validateInstance(new StreamSource( new StringReader(xml)));

		if (!result.isValid()) {
			for (ValidationProblem problem:result.getProblems()) {
				System.out.println(problem.getMessage());
			}
		}

		return result.isValid();
	}

	private LanguageDescription createLanguageDescription() throws JAXBException {
		LanguageDescription resource = (LanguageDescription) unmarshaller.unmarshal(new StringReader(LanguageDescriptionResourceXml));
		return resource;
	}

	private static String LanguageDescriptionResourceXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<!--Sample XML file generated by XMLSpy v2013 sp1 (http://www.altova.com)-->\r\n" + 
			"<ms:languageDescriptionMetadataRecord xsi:schemaLocation=\"http://www.meta-share.org/OMTD-SHARE_XMLSchema OMTD-SHARE-LanguageDescription.xsd\" xmlns:ms=\"http://www.meta-share.org/OMTD-SHARE_XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
			"	<ms:metadataHeaderInfo>\r\n" + 
			"		<ms:metadataRecordIdentifier metadataIdentifierSchemeName=\"other\" schemeURI=\"http://www.altova.com\">String</ms:metadataRecordIdentifier>\r\n" + 
			"		<ms:metadataCreationDate>2016-07-29</ms:metadataCreationDate>\r\n" + 
			"		<ms:metadataCreators>\r\n" + 
			"			<ms:metadataCreator>\r\n" + 
			"				<ms:personNames>\r\n" + 
			"					<ms:personName lang=\"en-us\">Smith, John</ms:personName>\r\n" + 
			"				</ms:personNames>\r\n" + 
			"			</ms:metadataCreator>\r\n" + 
			"		</ms:metadataCreators>\r\n" + 
			"		<ms:sourceOfMetadataRecord>\r\n" + 
			"			<ms:collectedFrom>\r\n" + 
			"				<ms:repositoryNames>\r\n" + 
			"					<ms:repositoryName lang=\"en-us\">META-SHARE</ms:repositoryName>\r\n" + 
			"				</ms:repositoryNames>\r\n" + 
			"			</ms:collectedFrom>\r\n" + 
			"			<ms:sourceMetadataLink>http://metashare.ilsp.gr:8080/repository/browse/an-lfg-grammar-of-polish-polfie/0f47a1046b0011e284b6000423bfd61c014dec2f462e43b3b9c814c0a8ac467a/</ms:sourceMetadataLink>\r\n" + 
			"			<ms:relatedMetadataScheme>META-SHARE</ms:relatedMetadataScheme>\r\n" + 
			"			<ms:originalDataProviderInfo>\r\n" + 
			"				<ms:originalDataProviderType>repository</ms:originalDataProviderType>\r\n" + 
			"				<ms:originalDataProviderRepository>\r\n" + 
			"					<ms:repositoryNames>\r\n" + 
			"						<ms:repositoryName lang=\"en-us\">Polish repository</ms:repositoryName>\r\n" + 
			"					</ms:repositoryNames>\r\n" + 
			"				</ms:originalDataProviderRepository>\r\n" + 
			"			</ms:originalDataProviderInfo>\r\n" + 
			"		</ms:sourceOfMetadataRecord>\r\n" + 
			"		<ms:metadataLanguages>\r\n" + 
			"			<ms:metadataLanguage>\r\n" + 
			"				<ms:languageTag>en</ms:languageTag>\r\n" + 
			"			</ms:metadataLanguage>\r\n" + 
			"		</ms:metadataLanguages>\r\n" + 
			"		<ms:metadataLastDateUpdated>2016-07-25</ms:metadataLastDateUpdated>\r\n" + 
			"	</ms:metadataHeaderInfo>\r\n" + 
			"	<ms:languageDescriptionInfo>\r\n" + 
			"		<ms:resourceType>languageDescription</ms:resourceType>\r\n" + 
			"		<ms:identificationInfo>\r\n" + 
			"			<ms:resourceNames>\r\n" + 
			"				<ms:resourceName lang=\"en-us\">An LFG grammar of Polish (POLFIE)</ms:resourceName>\r\n" + 
			"			</ms:resourceNames>\r\n" + 
			"			<ms:descriptions>\r\n" + 
			"				<ms:description lang=\"en-us\">POLFIE is an LFG grammar of Polish implemented in the XLE system (Xerox Linguistic Environment), it is being developed within NEKST project. It provides a two-layer representation: constituent structure (c-structure, tree representation) and functional structure (f-structure, AVM representation). It is based on two previous implemented grammars of Polish: its c-structure is based on GFJP2, a DCG grammar used by the parser Świgra, while its f-structure is inspired by FOJP, an HPSG grammar of Polish. Lexical entries used by the grammar are created using Morfeusz, the state-of-the-art morphological analyser for Polish, and converted valence dictionaries used by Świgra.</ms:description>\r\n" + 
			"			</ms:descriptions>\r\n" + 
			"			<ms:resourceShortNames>\r\n" + 
			"				<ms:resourceShortName lang=\"en-us\">POLFIE</ms:resourceShortName>\r\n" + 
			"			</ms:resourceShortNames>\r\n" + 
			"			<ms:identifiers>\r\n" + 
			"				<ms:identifier resourceIdentifierSchemeName=\"url\">http://zil.ipipan.waw.pl/LFG</ms:identifier>\r\n" + 
			"			</ms:identifiers>\r\n" + 
			"		</ms:identificationInfo>\r\n" + 
			"		<ms:contactInfo>\r\n" + 
			"			<ms:landingPage>http://zil.ipipan.waw.pl/LFG</ms:landingPage>\r\n" + 
			"		</ms:contactInfo>\r\n" + 
			"		<ms:validationInfos>\r\n" + 
			"			<ms:validationInfo>\r\n" + 
			"				<ms:validated>true</ms:validated>\r\n" + 
			"				<ms:validationType>content</ms:validationType>\r\n" + 
			"				<ms:validationMode>mixed</ms:validationMode>\r\n" + 
			"				<ms:validationModeDetails>testing validation</ms:validationModeDetails>\r\n" + 
			"				<ms:validationExtent>full</ms:validationExtent>\r\n" + 
			"				<ms:sizePerValidation>\r\n" + 
			"					<ms:size>100</ms:size>\r\n" + 
			"					<ms:sizeUnit>multiWordUnits</ms:sizeUnit>\r\n" + 
			"				</ms:sizePerValidation>\r\n" + 
			"				<ms:validationReports>\r\n" + 
			"					<ms:hasValidationReport>\r\n" + 
			"						<ms:publicationIdentifiers>\r\n" + 
			"							<ms:publicationIdentifier publicationIdentifierSchemeName=\"doi\">doi:10.1007/s10493-006-9035-0</ms:publicationIdentifier>\r\n" + 
			"						</ms:publicationIdentifiers>\r\n" + 
			"					</ms:hasValidationReport>\r\n" + 
			"				</ms:validationReports>\r\n" + 
			"				<ms:validators>\r\n" + 
			"					<ms:validator>\r\n" + 
			"						<ms:relatedOrganization>\r\n" + 
			"							<ms:organizationNames>\r\n" + 
			"								<ms:organizationName>Valiation organization</ms:organizationName>\r\n" + 
			"							</ms:organizationNames>\r\n" + 
			"						</ms:relatedOrganization>\r\n" + 
			"					</ms:validator>\r\n" + 
			"				</ms:validators>\r\n" + 
			"			</ms:validationInfo>\r\n" + 
			"		</ms:validationInfos>\r\n" + 
			"		<ms:usageInfo>\r\n" + 
			"			<ms:foreseenUseInfos>\r\n" + 
			"				<ms:foreseenUseInfo>\r\n" + 
			"					<ms:foreseenUse>nlpApplications</ms:foreseenUse>\r\n" + 
			"					<ms:useNlpApplications>\r\n" + 
			"						<ms:useNLPSpecific>visualSceneUnderstanding</ms:useNLPSpecific>\r\n" + 
			"					</ms:useNlpApplications>\r\n" + 
			"				</ms:foreseenUseInfo>\r\n" + 
			"				<ms:foreseenUseInfo>\r\n" + 
			"					<ms:foreseenUse>nlpApplications</ms:foreseenUse>\r\n" + 
			"					<ms:useNlpApplications>\r\n" + 
			"						<ms:useNLPSpecific>lexiconExtractionFromLexica</ms:useNLPSpecific>\r\n" + 
			"						<ms:useNLPSpecific>bilingualLexiconInduction</ms:useNLPSpecific>\r\n" + 
			"					</ms:useNlpApplications>\r\n" + 
			"				</ms:foreseenUseInfo>\r\n" + 
			"			</ms:foreseenUseInfos>\r\n" + 
			"			<ms:actualUseInfos>\r\n" + 
			"				<ms:actualUseInfo>\r\n" + 
			"					<ms:actualUse>humanUse</ms:actualUse>\r\n" + 
			"					<ms:usageReports>\r\n" + 
			"						<ms:usageReport>\r\n" + 
			"							<ms:documentUnstructured>Allassonniere, S., Kuhn, E., Trouve, A.: Bayesian deformable models building via stochastic approximation algorithm: A convergence study. Bernoulli J. 16(3), 641{ 678 (2010)</ms:documentUnstructured>\r\n" + 
			"						</ms:usageReport>\r\n" + 
			"					</ms:usageReports>\r\n" + 
			"					<ms:usageProjects>\r\n" + 
			"						<ms:usageProject>\r\n" + 
			"							<ms:projectNames>\r\n" + 
			"								<ms:projectName lang=\"en-us\">NSF | FRG: The Geometry, Mechanics and Statistics of the Infinite-dimensional Manifold of Shapes (0456253)</ms:projectName>\r\n" + 
			"							</ms:projectNames>\r\n" + 
			"						</ms:usageProject>\r\n" + 
			"					</ms:usageProjects>\r\n" + 
			"				</ms:actualUseInfo>\r\n" + 
			"			</ms:actualUseInfos>\r\n" + 
			"		</ms:usageInfo>\r\n" + 
			"		<ms:resourceCreationInfo>\r\n" + 
			"			<ms:resourceCreators>\r\n" + 
			"				<ms:resourceCreator>\r\n" + 
			"					<ms:relatedPerson>\r\n" + 
			"						<ms:personNames>\r\n" + 
			"							<ms:personName lang=\"en-us\">Creator, name</ms:personName>\r\n" + 
			"						</ms:personNames>\r\n" + 
			"					</ms:relatedPerson>\r\n" + 
			"				</ms:resourceCreator>\r\n" + 
			"			</ms:resourceCreators>\r\n" + 
			"			<ms:fundingProjects>\r\n" + 
			"				<ms:fundingProject>\r\n" + 
			"					<ms:projectNames>\r\n" + 
			"						<ms:projectName lang=\"en-us\">An adaptive system to support problem-solving on the basis of document collections in the Internet (NEKST) </ms:projectName>\r\n" + 
			"					</ms:projectNames>\r\n" + 
			"				</ms:fundingProject>\r\n" + 
			"			</ms:fundingProjects>\r\n" + 
			"			<ms:creationDate>\r\n" + 
			"				<ms:date>\r\n" + 
			"					<ms:year>1995</ms:year>\r\n" + 
			"				</ms:date>\r\n" + 
			"			</ms:creationDate>\r\n" + 
			"		</ms:resourceCreationInfo>\r\n" + 
			"		<ms:distributionInfos>\r\n" + 
			"			<ms:datasetDistributionInfo>\r\n" + 
			"				<ms:distributionMediums>\r\n" + 
			"					<ms:distributionMedium>webExecutable</ms:distributionMedium>\r\n" + 
			"				</ms:distributionMediums>\r\n" + 
			"				<ms:rightsInfo>\r\n" + 
			"					<ms:licenceInfos>\r\n" + 
			"						<ms:licenceInfo>\r\n" + 
			"							<ms:licence>CC-BY</ms:licence>\r\n" + 
			"						</ms:licenceInfo>\r\n" + 
			"					</ms:licenceInfos>\r\n" + 
			"				</ms:rightsInfo>\r\n" + 
			"			</ms:datasetDistributionInfo>\r\n" + 
			"		</ms:distributionInfos>\r\n" + 
			"		<ms:languageDescriptionType>grammar</ms:languageDescriptionType>\r\n" + 
			"		<ms:languageDescriptionEncodingInfo>\r\n" + 
			"			<ms:encodingLevels>\r\n" + 
			"			<ms:encodingLevel>syntax</ms:encodingLevel>\r\n" + 
			"			</ms:encodingLevels>\r\n" + 
			"			<ms:theoreticModels>\r\n" + 
			"			<ms:theoreticModel>LFG (Lexical Functional Grammar) grammars minimally provide two levels of representation: constituent structure (c-structure) produced by context-free phrase structure rules and functional structure (f-structure) created by functional descriptions.</ms:theoreticModel>\r\n" + 
			"			</ms:theoreticModels>\r\n" + 
			"			<ms:formalisms>\r\n" + 
			"			<ms:formalism>LFG</ms:formalism>\r\n" + 
			"</ms:formalisms>\r\n" + 
			"<ms:tasks>\r\n" + 
			"			<ms:task>titlesParsing</ms:task>\r\n" + 
			"			<ms:task>other</ms:task>\r\n" + 
			"</ms:tasks>\r\n" + 
			"	<ms:grammaticalPhenomenaCoverages>\r\n" + 
			"			<ms:grammaticalPhenomenaCoverage>anaphora</ms:grammaticalPhenomenaCoverage>\r\n" + 
			"			</ms:grammaticalPhenomenaCoverages>\r\n" + 
			"		</ms:languageDescriptionEncodingInfo>\r\n" + 
			"		<ms:languageDescriptionOperationInfo>\r\n" + 
			"			<ms:runningEnvironmentInfo>\r\n" + 
			"				<ms:requiresSoftware>\r\n" + 
			"					<ms:resourceIdentifiers>\r\n" + 
			"						<ms:resourceIdentifier resourceIdentifierSchemeName=\"ean13\" schemeURI=\"http://www.altova.com\">String</ms:resourceIdentifier>\r\n" + 
			"					</ms:resourceIdentifiers>\r\n" + 
			"				</ms:requiresSoftware>\r\n" + 
			"				<ms:requiresHardware>specialHardwareEquipment</ms:requiresHardware>\r\n" + 
			"				<ms:requiresLRs>\r\n" + 
			"					<ms:resourceIdentifiers>\r\n" + 
			"						<ms:resourceIdentifier resourceIdentifierSchemeName=\"oai\" schemeURI=\"http://www.altova.com\">String</ms:resourceIdentifier>\r\n" + 
			"					</ms:resourceIdentifiers>\r\n" + 
			"				</ms:requiresLRs>\r\n" + 
			"			</ms:runningEnvironmentInfo>\r\n" + 
			"		</ms:languageDescriptionOperationInfo>\r\n" + 
			"		<ms:languageDescriptionMediaType>\r\n" + 
			"			<ms:languageDescriptionTextInfo>\r\n" + 
			"				<ms:mediaType>text</ms:mediaType>\r\n" + 
			"				<ms:lingualityInfo>\r\n" + 
			"					<ms:lingualityType>monolingual</ms:lingualityType>\r\n" + 
			"				</ms:lingualityInfo>\r\n" + 
			"				<ms:languages>\r\n" + 
			"				<ms:languageInfo>\r\n" + 
			"					<ms:language>\r\n" + 
			"						<ms:languageTag>pl</ms:languageTag>\r\n" + 
			"					</ms:language>\r\n" + 
			"					<ms:languageVarieties>\r\n" + 
			"						<ms:languageVarietyInfo>\r\n" + 
			"							<ms:languageVarietyType>dialect</ms:languageVarietyType>\r\n" + 
			"							<ms:languageVarietyName>Polish dialect</ms:languageVarietyName>\r\n" + 
			"						</ms:languageVarietyInfo>\r\n" + 
			"					</ms:languageVarieties>\r\n" + 
			"				</ms:languageInfo>\r\n" + 
			"				</ms:languages>\r\n" + 
			"				<ms:modalities>\r\n" + 
			"				<ms:modalityInfo>\r\n" + 
			"					<ms:modalityType>writtenLanguage</ms:modalityType>\r\n" + 
			"				</ms:modalityInfo>\r\n" + 
			"				</ms:modalities>\r\n" + 
			"				<ms:sizes>\r\n" + 
			"				<ms:sizeInfo>\r\n" + 
			"					<ms:size>100</ms:size>\r\n" + 
			"					<ms:sizeUnit>rules</ms:sizeUnit>\r\n" + 
			"				</ms:sizeInfo>\r\n" + 
			"				</ms:sizes>\r\n" + 
			"						<ms:creationInfo>\r\n" + 
			"			<ms:originalSources>\r\n" + 
			"				<ms:hasOriginalSource>\r\n" + 
			"					<ms:resourceIdentifiers>\r\n" + 
			"						<ms:resourceIdentifier resourceIdentifierSchemeName=\"ark\" schemeURI=\"http://www.altova.com\">String</ms:resourceIdentifier>\r\n" + 
			"					</ms:resourceIdentifiers>\r\n" + 
			"				</ms:hasOriginalSource>\r\n" + 
			"			</ms:originalSources>\r\n" + 
			"			<ms:creationSwComponents>\r\n" + 
			"				<ms:isCreatedBy>\r\n" + 
			"					<ms:resourceIdentifiers>\r\n" + 
			"						<ms:resourceIdentifier resourceIdentifierSchemeName=\"hdl\" schemeURI=\"http://www.altova.com\">String</ms:resourceIdentifier>\r\n" + 
			"					</ms:resourceIdentifiers>\r\n" + 
			"				</ms:isCreatedBy>\r\n" + 
			"			</ms:creationSwComponents>\r\n" + 
			"		</ms:creationInfo>\r\n" + 
			"\r\n" + 
			"			</ms:languageDescriptionTextInfo>\r\n" + 
			"		</ms:languageDescriptionMediaType>\r\n" + 
			"	</ms:languageDescriptionInfo>\r\n" + 
			"</ms:languageDescriptionMetadataRecord>";
	
	private static String almostAllLanguageDescriptionResourceXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<!--Sample XML file generated by XMLSpy v2013 sp1 (http://www.altova.com)-->\r\n" + 
			"<ms:languageDescriptionMetadataRecord xsi:schemaLocation=\"http://www.meta-share.org/OMTD-SHARE_XMLSchema OMTD-SHARE-LanguageDescription.xsd\" xmlns:ms=\"http://www.meta-share.org/OMTD-SHARE_XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
			"	<ms:metadataHeaderInfo>\r\n" + 
			"		<ms:metadataRecordIdentifier metadataIdentifierSchemeName=\"other\" schemeURI=\"http://www.altova.com\">String</ms:metadataRecordIdentifier>\r\n" + 
			"		<ms:metadataCreationDate>2015-08-13</ms:metadataCreationDate>\r\n" + 
			"		<ms:metadataLastDateUpdated>2014-08-13</ms:metadataLastDateUpdated>\r\n" + 
			"	</ms:metadataHeaderInfo>\r\n" + 
			"	<ms:languageDescriptionInfo>\r\n" + 
			"		<ms:resourceType>languageDescription</ms:resourceType>\r\n" + 
			"		<ms:identificationInfo>\r\n" + 
			"			<ms:resourceNames>\r\n" + 
			"				<ms:resourceName lang=\"en-us\">Greek grammar</ms:resourceName>\r\n" + 
			"			</ms:resourceNames>\r\n" + 
			"			<ms:descriptions>\r\n" + 
			"				<ms:description lang=\"en-us\">Toy grammar of Greek</ms:description>\r\n" + 
			"			</ms:descriptions>\r\n" + 
			"			<ms:identifiers>\r\n" + 
			"				<ms:identifier resourceIdentifierSchemeName=\"doi\" schemeURI=\"http://www.altova.com\">String</ms:identifier>\r\n" + 
			"				<ms:identifier resourceIdentifierSchemeName=\"lsid\" schemeURI=\"http://www.altova.com\">String</ms:identifier>\r\n" + 
			"			</ms:identifiers>\r\n" + 
			"		</ms:identificationInfo>\r\n" + 
			"		<ms:contactInfo>\r\n" + 
			"			<ms:contactEmail/>\r\n" + 
			"		</ms:contactInfo>\r\n" + 
			"		<ms:distributionInfos>\r\n" + 
			"			<ms:datasetDistributionInfo>\r\n" + 
			"				<ms:distributionMediums>\r\n" + 
			"					<ms:distributionMedium>accessibleThroughInterface</ms:distributionMedium>\r\n" + 
			"				</ms:distributionMediums>\r\n" + 
			"				<ms:rightsInfo>\r\n" + 
			"					<ms:licenceInfos>\r\n" + 
			"						<ms:licenceInfo>\r\n" + 
			"							<ms:licence>GPL</ms:licence>\r\n" + 
			"						</ms:licenceInfo>\r\n" + 
			"					</ms:licenceInfos>\r\n" + 
			"				</ms:rightsInfo>\r\n" + 
			"			</ms:datasetDistributionInfo>\r\n" + 
			"		</ms:distributionInfos>\r\n" + 
			"		<ms:languageDescriptionType>grammar</ms:languageDescriptionType>\r\n" + 
			"		<ms:languageDescriptionMediaType>\r\n" + 
			"			<ms:languageDescriptionTextInfo>\r\n" + 
			"				<ms:mediaType>text</ms:mediaType>\r\n" + 
			"				<ms:lingualityInfo>\r\n" + 
			"					<ms:lingualityType>monolingual</ms:lingualityType>\r\n" + 
			"				</ms:lingualityInfo>\r\n" + 
			"				<ms:languages>\r\n" + 
			"					<ms:languageInfo>\r\n" + 
			"						<ms:language>\r\n" + 
			"							<ms:languageTag>el</ms:languageTag>\r\n" + 
			"						</ms:language>\r\n" + 
			"					</ms:languageInfo>\r\n" + 
			"				</ms:languages>\r\n" + 
			"				<ms:sizes>\r\n" + 
			"					<ms:sizeInfo>\r\n" + 
			"						<ms:size>116</ms:size>\r\n" + 
			"						<ms:sizeUnit>rules</ms:sizeUnit>\r\n" + 
			"					</ms:sizeInfo>\r\n" + 
			"				</ms:sizes>\r\n" + 
			"			</ms:languageDescriptionTextInfo></ms:languageDescriptionMediaType>\r\n" + 
			"	</ms:languageDescriptionInfo>\r\n" + 
			"</ms:languageDescriptionMetadataRecord>\r\n" + 
			"";
}
