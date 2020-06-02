package ru.vlsu.animalcertification.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ru.vlsu.animalcertification.web.rest.TestUtil;

public class PersonDataTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonData.class);
        PersonData personData1 = new PersonData();
        personData1.setId(1L);
        PersonData personData2 = new PersonData();
        personData2.setId(personData1.getId());
        assertThat(personData1).isEqualTo(personData2);
        personData2.setId(2L);
        assertThat(personData1).isNotEqualTo(personData2);
        personData1.setId(null);
        assertThat(personData1).isNotEqualTo(personData2);
    }
}
