package concept.model;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import concept.matcher.IsMatcher;

/**
 * Testing of class {@link concept.model.Model}.
 */
public class TestModel {
    /**
     * Testing normal construction of a model.
     */
    @Test
    public void testInit() {
        final Model model = new Model("persons", "about some persons");
        assertThat(model.getName(), equalTo("persons"));
        assertThat(model.getDescription(), equalTo("about some persons"));
        assertThat(model.hasSubjects(), equalTo(false));
    }

    /**
     * Testing of {@link concept.model.Model#addSubject(Subject)},
     * {@link concept.model.Model#getSubject(String)} and
     * {@link concept.model.Model#hasSubjects()}.
     */
    @Test
    public void testSubjects() {
        final Model model = new Model("persons", "about some persons");
        assertThat(model.addSubject(null), equalTo(false));
        assertThat(model.getSubject(null), equalTo((Subject) null));
        assertThat(model.getSubject("Agatha Christie"), equalTo((Subject) null));

        final Subject agathaChristie = new Subject("Agatha Christie", "author of books");
        assertThat(model.addSubject(agathaChristie), equalTo(true));
        assertThat(model.addSubject(agathaChristie), equalTo(false));
        assertThat(model.hasSubjects(), equalTo(true));
        assertThat(model.getSubject("Agatha Christie"), equalTo(agathaChristie));
    }

    /**
     * Testing of {@link concept.model.Model#toString()}.
     */
    @Test
    public void testToStringDefault() {
        final Model model = new Model("persons", "about some persons");
        final String expected = "Model[name=persons,description=about some persons]";
        assertThat(model.toString(), equalTo(expected));
    }

    /**
     * Testing of {@link concept.model.Model#toJson()} with just name and
     * description.
     */
    @Test
    public void testToJsonWithEmptyModel() {
        final Model model = new Model("persons", "about some persons");
        final StringBuilder json = new StringBuilder();
        json.append("{\"subjects\":{},");
        json.append("\"facts\":{\"data\":{}},");
        json.append("\"name\":\"persons\",\"description\":\"about some persons\"}");

        assertThat(model.toJson(), equalTo(json.toString()));
    }

    /**
     * Testing of {@link concept.model.Model#toJson()} with name, description
     * and one subject.
     */
    @Test
    public void testToJsonWithOneSubject() {
        final Model model = new Model("persons", "about some persons");
        model.addSubject(new Subject("Agatha Christie", "is a writer"));

        final StringBuilder json = new StringBuilder();
        json.append("{\"subjects\":");
        json.append("{\"Agatha Christie\":{\"name\":\"Agatha Christie\",\"description\":\"is a writer\"}},");
        json.append("\"facts\":{\"data\":{}},");
        json.append("\"name\":\"persons\",\"description\":\"about some persons\"}");

        assertThat(model.toJson(), equalTo(json.toString()));
    }

    /**
     * Testing of {@link concept.model.Subject#fromJson(String)}.
     */
    @Test
    public void testFromJsonWithEmptyModel() {
        final String json = "{\"subjects\":{},\"name\":\"persons\",\"description\":\"about some persons\"}";
        final Model model = Model.fromJson(json);
        assertThat(model, not((Model) null));
        assertThat(model.getName(), equalTo("persons"));
        assertThat(model.getDescription(), equalTo("about some persons"));
        assertThat(model.hasSubjects(), equalTo(false));
    }

    /**
     * Testing of {@link concept.model.Subject#fromJson(String)} with one
     * subject.
     */
    @Test
    public void testFromJsonWithOneSubject() {
        final StringBuilder json = new StringBuilder();
        json.append("{\"subjects\":");
        json.append("{\"Agatha Christie\":{\"name\":\"Agatha Christie\",\"description\":\"is a writer\"}},");
        json.append("\"name\":\"persons\",\"description\":\"about some persons\"}");

        final Model model = Model.fromJson(json.toString());
        assertThat(model, not((Model) null));
        assertThat(model.getName(), equalTo("persons"));
        assertThat(model.getDescription(), equalTo("about some persons"));
        assertThat(model.hasSubjects(), equalTo(true));
        final Subject subject = model.getSubject("Agatha Christie");
        assertThat(subject, not((Subject) null));
        assertThat(subject.getName(), equalTo("Agatha Christie"));
        assertThat(subject.getDescription(), equalTo("is a writer"));
    }

    /**
     * Testing of {@link concept.model.Subject#fromJson(String)} with bad JSON
     * format.
     */
    @Test
    public void testFromJsonWithBadJson() {
        final Model model = Model.fromJson("abc");
        assertThat(model, equalTo((Model) null));
    }

    /**
     * Testing of {@link concept.model.Subject#fromJson(String)} with "{}" as
     * JSON.
     */
    @Test
    public void testFromJsonWithEmptyJson() {
        final Model model = Model.fromJson("{}");
        assertThat(model, equalTo((Model) null));
    }

    /**
     * Testing of {@link concept.model.Model#addFact(Fact)}. You cannot add same
     * fact twice.
     */
    @Test
    public void testAddOneFact() {
        final Model model = new Model("persons", "about some persons");
        final Fact fact = new Fact("John", "one person", new IsMatcher<>(46));
        assertThat(model.addFact(fact), equalTo(true));
        assertThat(model.addFact(fact), equalTo(false));

        final Fact sameFact = new Fact("John", "one person", new IsMatcher<>(46));
        assertThat(model.addFact(sameFact), equalTo(false));
    }

}
