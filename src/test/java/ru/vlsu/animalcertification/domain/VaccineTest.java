package ru.vlsu.animalcertification.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ru.vlsu.animalcertification.web.rest.TestUtil;

public class VaccineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vaccine.class);
        Vaccine vaccine1 = new Vaccine();
        vaccine1.setId(1L);
        Vaccine vaccine2 = new Vaccine();
        vaccine2.setId(vaccine1.getId());
        assertThat(vaccine1).isEqualTo(vaccine2);
        vaccine2.setId(2L);
        assertThat(vaccine1).isNotEqualTo(vaccine2);
        vaccine1.setId(null);
        assertThat(vaccine1).isNotEqualTo(vaccine2);
    }
}
