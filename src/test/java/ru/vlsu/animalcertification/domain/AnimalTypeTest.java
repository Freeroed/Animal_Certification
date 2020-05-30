package ru.vlsu.animalcertification.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ru.vlsu.animalcertification.web.rest.TestUtil;

public class AnimalTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnimalType.class);
        AnimalType animalType1 = new AnimalType();
        animalType1.setId(1L);
        AnimalType animalType2 = new AnimalType();
        animalType2.setId(animalType1.getId());
        assertThat(animalType1).isEqualTo(animalType2);
        animalType2.setId(2L);
        assertThat(animalType1).isNotEqualTo(animalType2);
        animalType1.setId(null);
        assertThat(animalType1).isNotEqualTo(animalType2);
    }
}
