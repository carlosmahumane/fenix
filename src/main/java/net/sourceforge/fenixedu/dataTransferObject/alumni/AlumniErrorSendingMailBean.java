package net.sourceforge.fenixedu.dataTransferObject.alumni;

import java.io.Serializable;

import org.joda.time.YearMonthDay;

public class AlumniErrorSendingMailBean implements Serializable {

    private String contactEmail;
    private String documentIdNumber;
    private String fullName;
    private YearMonthDay dateOfBirthYearMonthDay;
    private String socialSecurityNumber;
    private String nameOfFather;
    private String nameOfMother;
    private Integer studentNumber;
    private String errorMessage;

    public AlumniErrorSendingMailBean() {
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDocumentIdNumber() {
        return documentIdNumber;
    }

    public void setDocumentIdNumber(String documentIdNumber) {
        this.documentIdNumber = documentIdNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public YearMonthDay getDateOfBirthYearMonthDay() {
        return dateOfBirthYearMonthDay;
    }

    public void setDateOfBirthYearMonthDay(YearMonthDay dateOfBirthYearMonthDay) {
        this.dateOfBirthYearMonthDay = dateOfBirthYearMonthDay;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getNameOfFather() {
        return nameOfFather;
    }

    public void setNameOfFather(String nameOfFather) {
        this.nameOfFather = nameOfFather;
    }

    public String getNameOfMother() {
        return nameOfMother;
    }

    public void setNameOfMother(String nameOfMother) {
        this.nameOfMother = nameOfMother;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
