package ru.vlsu.animalcertification.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ru.vlsu.animalcertification.web.rest.TestUtil;

public class BorderCrossingPointTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BorderCrossingPoint.class);
        BorderCrossingPoint borderCrossingPoint1 = new BorderCrossingPoint();
        borderCrossingPoint1.setId(1L);
        BorderCrossingPoint borderCrossingPoint2 = new BorderCrossingPoint();
        borderCrossingPoint2.setId(borderCrossingPoint1.getId());
        assertThat(borderCrossingPoint1).isEqualTo(borderCrossingPoint2);
        borderCrossingPoint2.setId(2L);
        assertThat(borderCrossingPoint1).isNotEqualTo(borderCrossingPoint2);
        borderCrossingPoint1.setId(null);
        assertThat(borderCrossingPoint1).isNotEqualTo(borderCrossingPoint2);
    }
}
