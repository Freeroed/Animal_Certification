package ru.vlsu.animalcertification.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ru.vlsu.animalcertification.web.rest.TestUtil;

public class LaboratoryResearchTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LaboratoryResearch.class);
        LaboratoryResearch laboratoryResearch1 = new LaboratoryResearch();
        laboratoryResearch1.setId(1L);
        LaboratoryResearch laboratoryResearch2 = new LaboratoryResearch();
        laboratoryResearch2.setId(laboratoryResearch1.getId());
        assertThat(laboratoryResearch1).isEqualTo(laboratoryResearch2);
        laboratoryResearch2.setId(2L);
        assertThat(laboratoryResearch1).isNotEqualTo(laboratoryResearch2);
        laboratoryResearch1.setId(null);
        assertThat(laboratoryResearch1).isNotEqualTo(laboratoryResearch2);
    }
}
