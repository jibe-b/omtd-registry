package eu.openminted.registry.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xmlunit.builder.Input;
import org.xmlunit.validation.Languages;
import org.xmlunit.validation.ValidationProblem;
import org.xmlunit.validation.ValidationResult;
import org.xmlunit.validation.Validator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by antleb on 9/6/16.
 */
public class TestCorpusSerialization {

	private static Marshaller marshaller;
	private static Unmarshaller unmarshaller;

	@BeforeClass
	public static void init() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);

		marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		unmarshaller = jc.createUnmarshaller();
	}

//	@Test
	public void serialize() throws JAXBException {
		Corpus corpus = createCorpus();

		System.out.println("Output:");
		marshaller.marshal(corpus, System.out);

		StringWriter sw = new StringWriter();
		marshaller.marshal(corpus, sw);

		Assert.assertEquals(true, isValidCorpusXml(sw.toString()));
	}

	@Test
	public void compare1() throws JAXBException {
		Corpus corpus = new Corpus();

		StringWriter sw = new StringWriter();
		marshaller.marshal(corpus, sw);

		Corpus c2 = (Corpus) unmarshaller.unmarshal(new StringReader(sw.toString()));
		StringWriter sw2 = new StringWriter();
		marshaller.marshal(c2, sw2);

		Assert.assertEquals(sw.toString(), sw2.toString());
	}

	@Test
	public void testUnmarshal() throws JAXBException {
		Corpus corpus = (Corpus) unmarshaller.unmarshal(new StringReader(corpusXml));


		Assert.assertEquals("String", corpus.getMetadataHeaderInfo().getMetadataRecordIdentifier().getId());
	}

//	@Test
	public void compare2() throws JAXBException {
		Corpus corpus = (Corpus) unmarshaller.unmarshal(new StringReader(corpusXml));
		StringWriter sw = new StringWriter();

		marshaller.marshal(corpus, sw);

		Assert.assertEquals(corpusXml, sw.toString());
	}

	@Test
	public void testJson() throws IOException {
		Corpus corpus = createCorpus();

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		StringWriter sw = new StringWriter();

		mapper.writeValue(sw, corpus);

//		System.out.println(sw.toString());
	}

	private boolean isValidCorpusXml(String xml) {
		Validator validator = Validator.forLanguage(Languages.W3C_XML_SCHEMA_NS_URI);

		validator.setSchemaSource(Input.fromURI("http://194.177.192.223/openminted/schema/OMTD-SHARE-Corpus.xsd").build());
		ValidationResult result = validator.validateInstance(new StreamSource( new StringReader(xml)));

		if (!result.isValid()) {
			for (ValidationProblem problem:result.getProblems()) {
				System.out.println(problem.getMessage());
			}
		}

		return result.isValid();
	}

	private Corpus createCorpus() {
		ObjectFactory of = new ObjectFactory();
		Corpus corpus = of.createCorpus();

		corpus.setMetadataHeaderInfo(new MetadataHeaderInfo());

		corpus.getMetadataHeaderInfo().setMetadataCreationDate(new Date());

		corpus.getMetadataHeaderInfo().setMetadataCreators(new ArrayList<>());
		corpus.getMetadataHeaderInfo().getMetadataCreators().add(new RelatedPerson());
		corpus.getMetadataHeaderInfo().getMetadataCreators().get(0).setPersonNames(Arrays.asList("Smith, John"));

		corpus.getMetadataHeaderInfo().setRevision("1.23");
		corpus.getMetadataHeaderInfo().setLanguages(new ArrayList<>());

		corpus.getMetadataHeaderInfo().setSourceOfMetadataRecord(new SourceOfMetadataRecord());
		corpus.getMetadataHeaderInfo().getSourceOfMetadataRecord().setCollectedFrom(new RelatedRepository());
		corpus.getMetadataHeaderInfo().getSourceOfMetadataRecord().getCollectedFrom().setRepositoryNames(Arrays.asList("clarin:el"));
		corpus.getMetadataHeaderInfo().getSourceOfMetadataRecord().setSourceMetadataLink("https://inventory.clarin.gr/resources/browse/ilsp-feature-based-multi-tiered-pos-tagger/9ff47a0e5af111e5a2e0aa3fc8d33ad8f8736d2c68654a37b471475f9f913baa/");


		corpus.getMetadataHeaderInfo().setMetadataLastUpdated(new Date());

		corpus.getMetadataHeaderInfo().setMetadataRecordIdentifier(new Identifier<>());
		corpus.getMetadataHeaderInfo().getMetadataRecordIdentifier().setId("id");
		corpus.getMetadataHeaderInfo().getMetadataRecordIdentifier().setSchema(MetadataHeaderInfo.MetadataIdentifierSchema.URL);
		corpus.getMetadataHeaderInfo().getMetadataRecordIdentifier().setUrl("http://www.foo.gr");

		corpus.setResourceIdentificationInfo(new ResourceIdentificationInfo());
		corpus.getResourceIdentificationInfo().setResourceNames(Arrays.asList("ILSP Feature-based multi-tiered POS Tagger"));
		corpus.getResourceIdentificationInfo().setDescriptions(Arrays.asList("FBT part-of-speech tagger for Greek texts."));
		corpus.getResourceIdentificationInfo().setResourceShortNames(Arrays.asList("ilsp_fbt"));
		corpus.getResourceIdentificationInfo().setResourceIdentifiers(Arrays.asList(new Identifier<ResourceIdentifierSchema>(ResourceIdentifierSchema.HDL, "http://hdl.grnet.gr/11500/ATHENA-0000-0000-23E8-3", null)));

		corpus.setContactInfo(new ContactInfo());
		corpus.getContactInfo().setContactEmail("prokopis@ilsp.gr");
		corpus.getContactInfo().setContactPersons(Arrays.asList(new RelatedPerson()));
		corpus.getContactInfo().getContactPersons().get(0).setPersonNames(Arrays.asList("Prokopidis, Prokopis"));

		corpus.setDistributionInfos(Arrays.asList(new DatasetDistributionInfo()));
		corpus.getDistributionInfos().get(0).setDistributionMediums(Arrays.asList(DatasetDistributionInfo.DistributionMedium.ACCESSIBLE_THROUGH_INTERFACE));
		corpus.getDistributionInfos().get(0).setRightsInfo(new RightsInfo());
		corpus.getDistributionInfos().get(0).getRightsInfo().setLicenseInfos(Arrays.asList(new LicenseInfo()));
		corpus.getDistributionInfos().get(0).getRightsInfo().getLicenseInfos().get(0).setLicense(LicenseInfo.License.APACHE_LICENSE_2_0);


		corpus.setCorpusSubTypeSpecificInfo(new CorpusSubTypeSpecificInfo());
		corpus.getCorpusSubTypeSpecificInfo().setAnnotatedCorpusInfo(new AnnotatedCorpusInfo());

		corpus.getCorpusSubTypeSpecificInfo().getAnnotatedCorpusInfo().setAnnotations(new ArrayList<>());
		corpus.getCorpusSubTypeSpecificInfo().getAnnotatedCorpusInfo().getAnnotations().add(new AnnotationInfo());
		corpus.getCorpusSubTypeSpecificInfo().getAnnotatedCorpusInfo().getAnnotations().get(0).setAnnotationLevel(new AnnotationLevel("ee"));

		corpus.getCorpusSubTypeSpecificInfo().getAnnotatedCorpusInfo().setCorpusTextParts(new ArrayList<>());
		corpus.getCorpusSubTypeSpecificInfo().getAnnotatedCorpusInfo().getCorpusTextParts().add(new CorpusTextPartInfo());
		corpus.getCorpusSubTypeSpecificInfo().getAnnotatedCorpusInfo().getCorpusTextParts().get(0).setCreationInfo(new CreationInfo());
		return corpus;
	}

	private static String corpusXml =
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<ms:corpusMetadataRecord xsi:schemaLocation=\"http://www.meta-share.org/OMTD-SHARE_XMLSchema OMTD-SHARE-Corpus.xsd\" xmlns:ms=\"http://www.meta-share.org/OMTD-SHARE_XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
			"\t<ms:metadataHeaderInfo>\n" +
			"\t\t<ms:metadataRecordIdentifier metadataIdentifierSchemeName=\"url\" schemeURI=\"http://www.altova.com\">String</ms:metadataRecordIdentifier>\n" +
			"\t\t<ms:metadataCreationDate>2016-07-29</ms:metadataCreationDate>\n" +
			"\t\t<ms:metadataCreators>\n" +
			"\t\t\t<ms:metadataCreator>\n" +
			"\t\t\t\t<ms:personNames>\n" +
			"\t\t\t\t\t<ms:personName lang=\"en-us\">Smith, John</ms:personName>\n" +
			"\t\t\t\t</ms:personNames>\n" +
			"\t\t\t</ms:metadataCreator>\n" +
			"\t\t</ms:metadataCreators>\n" +
			"\t\t<ms:sourceOfMetadataRecord>\n" +
			"\t\t\t<ms:collectedFrom>\n" +
			"\t\t\t\t<ms:repositoryNames>\n" +
			"\t\t\t\t\t<ms:repositoryName lang=\"en-us\">clarin:el</ms:repositoryName>\n" +
			"\t\t\t\t</ms:repositoryNames>\n" +
			"\t\t\t</ms:collectedFrom>\n" +
			"\t\t\t<ms:sourceMetadataLink>https://inventory.clarin.gr/resources/browse/hellenic-national-corpus/72fa1f96588e11e59dc0aa3fc0687644c406cbcaba454c1c8d415213d99912bd/</ms:sourceMetadataLink>\n" +
			"\t\t\t<ms:relatedMetadataScheme>meta-share</ms:relatedMetadataScheme>\n" +
			"\t\t\t<ms:originalDataProviderInfo>\n" +
			"\t\t\t\t<ms:originalDataProviderType>repository</ms:originalDataProviderType>\n" +
			"\t\t\t\t<ms:originalDataProviderRepository>\n" +
			"\t\t\t\t\t<ms:repositoryNames>\n" +
			"\t\t\t\t\t\t<ms:repositoryName lang=\"en-us\">clarin:el</ms:repositoryName>\n" +
			"\t\t\t\t\t</ms:repositoryNames>\n" +
			"\t\t\t\t</ms:originalDataProviderRepository>\n" +
			"\t\t\t</ms:originalDataProviderInfo>\n" +
			"\t\t</ms:sourceOfMetadataRecord>\n" +
			"\t\t<ms:metadataLanguages>\n" +
			"\t\t\t<ms:metadataLanguage>\n" +
			"\t\t\t\t<ms:languageTag>el</ms:languageTag>\n" +
			"\t\t\t</ms:metadataLanguage>\n" +
			"\t\t</ms:metadataLanguages>\n" +
			"\t\t<ms:metadataLastDateUpdated>2016-07-24</ms:metadataLastDateUpdated>\n" +
			"\t\t<ms:revision>added language</ms:revision>\n" +
			"\t</ms:metadataHeaderInfo>\n" +
			"\t<ms:corpusInfo>\n" +
			"\t\t<ms:resourceType>corpus</ms:resourceType>\n" +
			"\t\t<ms:identificationInfo>\n" +
			"\t\t\t<ms:resourceNames>\n" +
			"\t\t\t\t<ms:resourceName lang=\"en-us\">Hellenic National Corpus</ms:resourceName>\n" +
			"\t\t\t</ms:resourceNames>\n" +
			"\t\t\t<ms:descriptions>\n" +
			"\t\t\t\t<ms:description lang=\"en-us\">The Hellenic National Corpus (HNC) currently contains about 47.000.000 words, and is constantly being updated. It consists of samples of written language exclusively. Texts in the HNC represent modern Greek language use. Most of them have been written after 1990. Texts written in highly idiomatic language have been excluded; high readability (high circulation newspapers, best-selling books etc.) is one of the main selection criteria. Texts from several media, belonging to different genres and dealing with various topics have been selected. These texts have been given to ILSP by copyright owners for research purposes only.</ms:description>\n" +
			"\t\t\t\t<ms:description lang=\"el\">Σώμα γενικών κειμένων της νέας ελληνικής, συνολικού μεγέθους 47 εκατ. λέξεων, το οποίο συνεχώς εμπλουτίζεται με νέα κείμενα. Τα κείμενα που περιλαμβάνονται στον ΕΘΕΓ έχουν επιλεγεί ως αντιπροσωπευτικά της σύγχρονης ελληνικής γλώσσας και χρονολογούνται, στην πλειονότητά τους, από το 1990 και μετά. Αποφεύγονται τα κείμενα με διαλεκτικές ή άλλες ιδιαιτερότητες και επιλέγονται κυρίως κείμενα με υψηλή αναγνωσιμότητα (εφημερίδες μεγάλης κυκλοφορίας, βιβλία με υψηλές πωλήσεις κτλ). Με στόχο την αντιπροσώπευση διαφορετικών επιπέδων λόγου, επιλέχθηκαν κείμενα από πολλές πηγές, που καλύπτουν ποικίλα κειμενικά είδη με ποικίλη θεματολογία. Τα κείμενα έχουν παραχωρηθεί στο ΙΕΛ με απώτερο σκοπό τη χρήση τους για ερευνητικούς σκοπούς.</ms:description>\n" +
			"\t\t\t</ms:descriptions>\n" +
			"\t\t\t<ms:resourceShortNames>\n" +
			"\t\t\t\t<ms:resourceShortName lang=\"en-us\">HNC</ms:resourceShortName>\n" +
			"\t\t\t\t<ms:resourceShortName lang=\"el\">ΕΘΕΓ</ms:resourceShortName>\n" +
			"\t\t\t</ms:resourceShortNames>\n" +
			"\t\t\t<ms:identifiers>\n" +
			"\t\t\t\t<ms:identifier resourceIdentifierSchemeName=\"hdl\">http://hdl.grnet.gr/11500/ATHENA-0000-0000-23E2-9</ms:identifier>\n" +
			"\t\t\t</ms:identifiers>\n" +
			"\t\t</ms:identificationInfo>\n" +
			"\t\t<ms:contactInfo>\n" +
			"\t\t\t<ms:landingPage>http://hnc.ilsp.gr</ms:landingPage>\n" +
			"\t\t</ms:contactInfo>\n" +
			"\t\t<ms:versionInfo>\n" +
			"\t\t\t<ms:version>v 1.9</ms:version>\n" +
			"\t\t</ms:versionInfo>\n" +
			"\t\t<ms:usageInfo>\n" +
			"\t\t\t<ms:foreseenUseInfos>\n" +
			"\t\t\t\t<ms:foreseenUseInfo>\n" +
			"\t\t\t\t\t<ms:foreseenUse>nlpApplications</ms:foreseenUse>\n" +
			"\t\t\t\t\t<ms:useNlpApplications>\n" +
			"\t\t\t\t\t\t<ms:useNLPSpecific>languageModelling</ms:useNLPSpecific>\n" +
			"\t\t\t\t\t</ms:useNlpApplications>\n" +
			"\t\t\t\t</ms:foreseenUseInfo>\n" +
			"\t\t\t\t<ms:foreseenUseInfo>\n" +
			"\t\t\t\t\t<ms:foreseenUse>humanUse</ms:foreseenUse>\n" +
			"\t\t\t\t</ms:foreseenUseInfo>\n" +
			"\t\t\t</ms:foreseenUseInfos>\n" +
			"\t\t\t<ms:actualUseInfos>\n" +
			"\t\t\t\t<ms:actualUseInfo>\n" +
			"\t\t\t\t\t<ms:actualUse>humanUse</ms:actualUse>\n" +
			"\t\t\t\t</ms:actualUseInfo>\n" +
			"\t\t\t\t<ms:actualUseInfo>\n" +
			"\t\t\t\t\t<ms:actualUse>nlpApplications</ms:actualUse>\n" +
			"\t\t\t\t\t<ms:useNlpApplications>\n" +
			"\t\t\t\t\t\t<ms:useNLPSpecific>emotionGeneration</ms:useNLPSpecific>\n" +
			"\t\t\t\t\t\t<ms:useNLPSpecific>personIdentification</ms:useNLPSpecific>\n" +
			"\t\t\t\t\t</ms:useNlpApplications>\n" +
			"\t\t\t\t</ms:actualUseInfo>\n" +
			"\t\t\t</ms:actualUseInfos>\n" +
			"\t\t</ms:usageInfo>\n" +
			"\t\t<ms:resourceDocumentationInfo>\n" +
			"\t\t\t<ms:mustBeCitedWith>\n" +
			"\t\t\t\t<ms:publicationIdentifiers>\n" +
			"\t\t\t\t\t<ms:publicationIdentifier publicationIdentifierSchemeName=\"url\">http://www.lrec-conf.org/proceedings/lrec2000/pdf/336.pdf</ms:publicationIdentifier>\n" +
			"\t\t\t\t</ms:publicationIdentifiers>\n" +
			"\t\t\t</ms:mustBeCitedWith>\n" +
			"\t\t</ms:resourceDocumentationInfo>\n" +
			"\t\t<ms:resourceCreationInfo>\n" +
			"\t\t\t<ms:resourceCreators>\n" +
			"\t\t\t\t<ms:resourceCreator>\n" +
			"\t\t\t\t\t<ms:relatedOrganization>\n" +
			"\t\t\t\t\t\t<ms:organizationNames>\n" +
			"\t\t\t\t\t\t\t<ms:organizationName>ILSP</ms:organizationName>\n" +
			"\t\t\t\t\t\t</ms:organizationNames>\n" +
			"\t\t\t\t\t</ms:relatedOrganization>\n" +
			"\t\t\t\t</ms:resourceCreator>\n" +
			"\t\t\t</ms:resourceCreators>\n" +
			"\t\t\t<ms:creationDate>\n" +
			"\t\t\t\t<ms:date>\n" +
			"\t\t\t\t\t<ms:year>1996</ms:year>\n" +
			"\t\t\t\t</ms:date>\n" +
			"\t\t\t</ms:creationDate>\n" +
			"\t\t</ms:resourceCreationInfo>\n" +
			"\t\t<ms:distributionInfos>\n" +
			"\t\t\t<ms:datasetDistributionInfo>\n" +
			"\t\t\t\t<ms:distributionMediums>\n" +
			"\t\t\t\t\t<ms:distributionMedium>accessibleThroughInterface</ms:distributionMedium>\n" +
			"\t\t\t\t</ms:distributionMediums>\n" +
			"\t\t\t\t<ms:accessURLs>\n" +
			"\t\t\t\t\t<ms:accessURL>http://hnc.ilsp.gr</ms:accessURL>\n" +
			"\t\t\t\t</ms:accessURLs>\n" +
			"\t\t\t\t<ms:textFormats>\n" +
			"\t\t\t\t\t<ms:textFormatInfo>\n" +
			"\t\t\t\t\t\t<ms:mimeType>application/x-msaccess</ms:mimeType>\n" +
			"\t\t\t\t\t</ms:textFormatInfo>\n" +
			"\t\t\t\t</ms:textFormats>\n" +
			"\t\t\t\t<ms:characterEncodings>\n" +
			"\t\t\t\t\t<ms:characterEncodingInfo>\n" +
			"\t\t\t\t\t\t<ms:characterEncoding>UTF-8</ms:characterEncoding>\n" +
			"\t\t\t\t\t</ms:characterEncodingInfo>\n" +
			"\t\t\t\t</ms:characterEncodings>\n" +
			"\t\t\t\t<ms:sizes>\n" +
			"\t\t\t\t\t<ms:sizeInfo>\n" +
			"\t\t\t\t\t\t<ms:size>100 million</ms:size>\n" +
			"\t\t\t\t\t\t<ms:sizeUnit>words</ms:sizeUnit>\n" +
			"\t\t\t\t\t</ms:sizeInfo>\n" +
			"\t\t\t\t</ms:sizes>\n" +
			"\t\t\t\t<ms:rightsInfo>\n" +
			"\t\t\t\t\t<ms:licenceInfos>\n" +
			"\t\t\t\t\t\t<ms:licenceInfo>\n" +
			"\t\t\t\t\t\t\t<ms:licence>CC-BY-NC</ms:licence>\n" +
			"\t\t\t\t\t\t\t<ms:version>4.0</ms:version>\n" +
			"\t\t\t\t\t\t\t<ms:conditionsOfUse>attribution</ms:conditionsOfUse>\n" +
			"\t\t\t\t\t\t\t<ms:conditionsOfUse>nonCommercialUse</ms:conditionsOfUse>\n" +
			"\t\t\t\t\t\t</ms:licenceInfo>\n" +
			"\t\t\t\t\t</ms:licenceInfos>\n" +
			"\t\t\t\t</ms:rightsInfo>\n" +
			"\t\t\t\t<ms:attributionTexts>\n" +
			"\t\t\t\t\t<ms:attributionText lang=\"en-us\">HNC licensed under CC-BY-NC 4.0 by ILSP</ms:attributionText>\n" +
			"\t\t\t\t</ms:attributionTexts>\n" +
			"\t\t\t</ms:datasetDistributionInfo>\n" +
			"\t\t</ms:distributionInfos>\n" +
			"\t\t<ms:corpusSubtypeSpecificInfo>\n" +
			"\t\t\t<ms:annotatedCorpusInfo>\n" +
			"\t\t\t\t<ms:corpusSubtype>annotatedCorpus</ms:corpusSubtype>\n" +
			"\t\t\t\t<ms:corpusMediaParts>\n" +
			"\t\t\t\t\t<ms:corpusTextParts>\n" +
			"\t\t\t\t\t\t<ms:corpusTextPartInfo>\n" +
			"\t\t\t\t\t\t\t<ms:mediaType>text</ms:mediaType>\n" +
			"\t\t\t\t\t\t\t<ms:lingualityInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:lingualityType>monolingual</ms:lingualityType>\n" +
			"\t\t\t\t\t\t\t</ms:lingualityInfo>\n" +
			"\t\t\t\t\t\t\t<ms:languages>\n" +
			"\t\t\t\t\t\t\t\t<ms:languageInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:language>\n" +
			"\t\t\t\t\t\t\t\t\t\t<ms:languageTag>el</ms:languageTag>\n" +
			"\t\t\t\t\t\t\t\t\t</ms:language>\n" +
			"\t\t\t\t\t\t\t\t</ms:languageInfo>\n" +
			"\t\t\t\t\t\t\t</ms:languages>\n" +
			"\n" +
			"\t\t\t\t\t\t\t<ms:modalities>\n" +
			"\t\t\t\t\t\t\t\t<ms:modalityInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:modalityType>writtenLanguage</ms:modalityType>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:modalityType>spokenLanguage</ms:modalityType>\n" +
			"\t\t\t\t\t\t\t\t</ms:modalityInfo>\n" +
			"\t\t\t\t\t\t\t</ms:modalities>\n" +
			"\t\t\t\t\t\t\t<ms:sizes>\n" +
			"\t\t\t\t\t\t\t\t<ms:sizeInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:size>100 million</ms:size>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:sizeUnit>words</ms:sizeUnit>\n" +
			"\t\t\t\t\t\t\t\t</ms:sizeInfo>\n" +
			"\t\t\t\t\t\t\t</ms:sizes>\n" +
			"\t\t\t\t\t\t\t<ms:textFormats>\n" +
			"\t\t\t\t\t\t\t\t<ms:textFormatInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:mimeType>application/x-msaccess</ms:mimeType>\n" +
			"\t\t\t\t\t\t\t\t</ms:textFormatInfo>\n" +
			"\t\t\t\t\t\t\t</ms:textFormats>\n" +
			"\t\t\t\t\t\t\t<ms:characterEncodings>\n" +
			"\t\t\t\t\t\t\t\t<ms:characterEncodingInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:characterEncoding>UTF-8</ms:characterEncoding>\n" +
			"\t\t\t\t\t\t\t\t</ms:characterEncodingInfo>\n" +
			"\t\t\t\t\t\t\t</ms:characterEncodings>\n" +
			"\t\t\t\t\t\t\t<ms:domains>\n" +
			"\t\t\t\t\t\t\t\t<ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:domain classificationSchemeName=\"PAROLE_topicClassification\">leisure</ms:domain>\n" +
			"\t\t\t\t\t\t\t\t</ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:domain classificationSchemeName=\"PAROLE_topicClassification\">geography</ms:domain>\n" +
			"\t\t\t\t\t\t\t\t</ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:domain classificationSchemeName=\"PAROLE_topicClassification\">science</ms:domain>\n" +
			"\t\t\t\t\t\t\t\t</ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:domain classificationSchemeName=\"PAROLE_topicClassification\">business</ms:domain>\n" +
			"\t\t\t\t\t\t\t\t</ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:domain classificationSchemeName=\"PAROLE_topicClassification\">history</ms:domain>\n" +
			"\t\t\t\t\t\t\t\t</ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:domain classificationSchemeName=\"PAROLE_topicClassification\">society</ms:domain>\n" +
			"\t\t\t\t\t\t\t\t</ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:domain classificationSchemeName=\"PAROLE_topicClassification\">humanities</ms:domain>\n" +
			"\t\t\t\t\t\t\t\t</ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:domain classificationSchemeName=\"PAROLE_topicClassification\">health</ms:domain>\n" +
			"\t\t\t\t\t\t\t\t</ms:domainInfo>\n" +
			"\t\t\t\t\t\t\t</ms:domains>\n" +
			"\t\t\t\t\t\t\t<ms:textClassifications>\n" +
			"\t\t\t\t\t\t\t\t<ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:textGenre classificationSchemeName=\"PAROLE_genreClassification\">non-fiction</ms:textGenre>\n" +
			"\t\t\t\t\t\t\t\t</ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:textGenre classificationSchemeName=\"PAROLE_genreClassification\">advertising</ms:textGenre>\n" +
			"\t\t\t\t\t\t\t\t</ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:textGenre classificationSchemeName=\"PAROLE_genreClassification\">official</ms:textGenre>\n" +
			"\t\t\t\t\t\t\t\t</ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:textGenre classificationSchemeName=\"PAROLE_genreClassification\">fiction</ms:textGenre>\n" +
			"\t\t\t\t\t\t\t\t</ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:textGenre classificationSchemeName=\"PAROLE_genreClassification\">private</ms:textGenre>\n" +
			"\t\t\t\t\t\t\t\t</ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:textGenre classificationSchemeName=\"PAROLE_genreClassification\">information</ms:textGenre>\n" +
			"\t\t\t\t\t\t\t\t</ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t<ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:textGenre classificationSchemeName=\"PAROLE_genreClassification\">discussion</ms:textGenre>\n" +
			"\t\t\t\t\t\t\t\t</ms:textClassificationInfo>\n" +
			"\t\t\t\t\t\t\t</ms:textClassifications>\n" +
			"\t\t\t\t\t\t\t<ms:timeClassifications>\n" +
			"\t\t\t\t\t\t\t\t<ms:timeCoverageInfo>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:timeCoverage>modern Greek after 1980</ms:timeCoverage>\n" +
			"\t\t\t\t\t\t\t\t</ms:timeCoverageInfo>\n" +
			"\t\t\t\t\t\t\t</ms:timeClassifications>\n" +
			"\t\t\t\t\t\t</ms:corpusTextPartInfo>\n" +
			"\t\t\t\t\t</ms:corpusTextParts>\n" +
			"\t\t\t\t\t<ms:annotations>\n" +
			"\t\t\t\t\t\t<ms:annotationInfo>\n" +
			"\t\t\t\t\t\t\t<ms:annotationLevel>morphosyntacticAnnotation-bPosTagging</ms:annotationLevel>\n" +
			"\t\t\t\t\t\t\t<ms:annotationStandoff>true</ms:annotationStandoff>\n" +
			"\t\t\t\t\t\t\t<ms:dataFormat>\n" +
			"\t\t\t\t\t\t\t\t<ms:mimeType>text/xml</ms:mimeType>\n" +
			"\t\t\t\t\t\t\t</ms:dataFormat>\n" +
			"\t\t\t\t\t\t\t<ms:tagset>\n" +
			"\t\t\t\t\t\t\t\t<ms:resourceNames>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:resourceName>ILSP/PAROLE tagset</ms:resourceName>\n" +
			"\t\t\t\t\t\t\t\t</ms:resourceNames>\n" +
			"\t\t\t\t\t\t\t</ms:tagset>\n" +
			"\t\t\t\t\t\t\t<ms:annotationMode>automatic</ms:annotationMode>\n" +
			"\t\t\t\t\t\t\t<ms:isAnnotatedBy>\n" +
			"\t\t\t\t\t\t\t\t<ms:resourceNames>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:resourceName>ILSP tagger</ms:resourceName>\n" +
			"\t\t\t\t\t\t\t\t</ms:resourceNames>\n" +
			"\t\t\t\t\t\t\t</ms:isAnnotatedBy>\n" +
			"\t\t\t\t\t\t</ms:annotationInfo>\n" +
			"\t\t\t\t\t\t<ms:annotationInfo>\n" +
			"\t\t\t\t\t\t\t<ms:annotationLevel>lemmatization</ms:annotationLevel>\n" +
			"\t\t\t\t\t\t\t<ms:annotationStandoff>true</ms:annotationStandoff>\n" +
			"\t\t\t\t\t\t\t<ms:dataFormat>\n" +
			"\t\t\t\t\t\t\t\t<ms:mimeType>text/xml</ms:mimeType>\n" +
			"\t\t\t\t\t\t\t</ms:dataFormat>\n" +
			"\t\t\t\t\t\t\t<ms:annotationMode>automatic</ms:annotationMode>\n" +
			"\t\t\t\t\t\t\t<ms:isAnnotatedBy>\n" +
			"\t\t\t\t\t\t\t\t<ms:resourceNames>\n" +
			"\t\t\t\t\t\t\t\t\t<ms:resourceName>ILSP lemmatizer</ms:resourceName>\n" +
			"\t\t\t\t\t\t\t\t</ms:resourceNames>\n" +
			"\t\t\t\t\t\t\t</ms:isAnnotatedBy>\n" +
			"\t\t\t\t\t\t</ms:annotationInfo>\n" +
			"\t\t\t\t\t</ms:annotations>\n" +
			"\t\t\t\t</ms:corpusMediaParts>\n" +
			"\t\t\t</ms:annotatedCorpusInfo>\n" +
			"\t\t</ms:corpusSubtypeSpecificInfo>\n" +
			"\t</ms:corpusInfo>\n" +
			"</ms:corpusMetadataRecord>\n";
}