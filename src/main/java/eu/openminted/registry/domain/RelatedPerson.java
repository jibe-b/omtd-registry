package eu.openminted.registry.domain;

import java.util.List;

/**
 * Created by stefania on 9/5/16.
 */
public class RelatedPerson {

    enum PersonIdentifierSchema implements IdentifierSchema {

        ORCID("orcid"),
        ISNI("isni"),
        RESEARCHER_ID("researcherId"),
        SCOPUS_ID("scopusId"),
        OTHER("other");

        private String value;

        PersonIdentifierSchema(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    //one of the two
    private List<String> personNames;
    private List<Identifier<PersonIdentifierSchema>> personIdentifiers;

    public RelatedPerson() {
    }

    public List<String> getPersonNames() {
        return personNames;
    }

    public void setPersonNames(List<String> personNames) {
        this.personNames = personNames;
    }

    public List<Identifier<PersonIdentifierSchema>> getPersonIdentifiers() {
        return personIdentifiers;
    }

    public void setPersonIdentifiers(List<Identifier<PersonIdentifierSchema>> personIdentifiers) {
        this.personIdentifiers = personIdentifiers;
    }
}
