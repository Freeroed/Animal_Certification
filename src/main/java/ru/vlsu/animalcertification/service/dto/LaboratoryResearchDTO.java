package ru.vlsu.animalcertification.service.dto;

import ru.vlsu.animalcertification.domain.enumeration.LaboratoryTestResult;

import java.io.Serializable;
import java.util.Objects;

public class LaboratoryResearchDTO implements Serializable {

    private Long id;

    private String laboratoty;

    private String indicator;

    private String resultReceiptDate;

    private String resurchMethod;

    private String examinationNumber;

    private LaboratoryTestResult result;

    private AnimalDTO animal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaboratoty() {
        return laboratoty;
    }

    public void setLaboratoty(String laboratoty) {
        this.laboratoty = laboratoty;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getResultReceiptDate() {
        return resultReceiptDate;
    }

    public void setResultReceiptDate(String resultReceiptDate) {
        this.resultReceiptDate = resultReceiptDate;
    }

    public String getResurchMethod() {
        return resurchMethod;
    }

    public void setResurchMethod(String resurchMethod) {
        this.resurchMethod = resurchMethod;
    }

    public String getExaminationNumber() {
        return examinationNumber;
    }

    public void setExaminationNumber(String examinationNumber) {
        this.examinationNumber = examinationNumber;
    }

    public LaboratoryTestResult getResult() {
        return result;
    }

    public void setResult(LaboratoryTestResult result) {
        this.result = result;
    }

    public AnimalDTO getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalDTO animal) {
        this.animal = animal;
    }

    public LaboratoryResearchDTO() {
    }

    @Override
    public String toString() {
        return "LaboratoryResearchDTO{" +
            "id=" + id +
            ", laboratoty='" + laboratoty + '\'' +
            ", indicator='" + indicator + '\'' +
            ", resultReceiptDate='" + resultReceiptDate + '\'' +
            ", resurchMethod='" + resurchMethod + '\'' +
            ", examinationNumber='" + examinationNumber + '\'' +
            ", result=" + result +
            ", animal=" + animal +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaboratoryResearchDTO that = (LaboratoryResearchDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(laboratoty, that.laboratoty) &&
            Objects.equals(indicator, that.indicator) &&
            Objects.equals(resultReceiptDate, that.resultReceiptDate) &&
            Objects.equals(resurchMethod, that.resurchMethod) &&
            Objects.equals(examinationNumber, that.examinationNumber) &&
            result == that.result &&
            Objects.equals(animal, that.animal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, laboratoty, indicator, resultReceiptDate, resurchMethod, examinationNumber, result, animal);
    }
}
