/*
 * Created on 6/Jan/2004
 *  
 */
package net.sourceforge.fenixedu.domain;

import java.util.Comparator;

import org.apache.struts.util.MessageResources;
import org.fenixedu.bennu.core.domain.Bennu;

/**
 * @author T�nia Pous�o
 * 
 */
public class PaymentPhase extends PaymentPhase_Base {

    public static Comparator<PaymentPhase> COMPARATOR_BY_END_DATE = new Comparator<PaymentPhase>() {
        @Override
        public int compare(PaymentPhase leftPaymentPhase, PaymentPhase rightPaymentPhase) {
            int comparationResult =
                    leftPaymentPhase.getEndDateYearMonthDay().compareTo(rightPaymentPhase.getEndDateYearMonthDay());
            return (comparationResult == 0) ? leftPaymentPhase.getExternalId().compareTo(rightPaymentPhase.getExternalId()) : comparationResult;
        }
    };

    public PaymentPhase() {
        super();
        setRootDomainObject(Bennu.getInstance());
    }

    public String getDescriptionFromMessageResourses() {
        MessageResources messages = MessageResources.getMessageResources("resources.ApplicationResources");

        String newDescription = null;
        newDescription = messages.getMessage(super.getDescription());
        if (newDescription == null) {
            newDescription = super.getDescription();
        }
        return newDescription;
    }

    public void delete() {
        setGratuityValues(null);
        setRootDomainObject(null);
        deleteDomainObject();
    }

    @Deprecated
    public java.util.Date getEndDate() {
        org.joda.time.YearMonthDay ymd = getEndDateYearMonthDay();
        return (ymd == null) ? null : new java.util.Date(ymd.getYear() - 1900, ymd.getMonthOfYear() - 1, ymd.getDayOfMonth());
    }

    @Deprecated
    public void setEndDate(java.util.Date date) {
        if (date == null) {
            setEndDateYearMonthDay(null);
        } else {
            setEndDateYearMonthDay(org.joda.time.YearMonthDay.fromDateFields(date));
        }
    }

    @Deprecated
    public java.util.Date getStartDate() {
        org.joda.time.YearMonthDay ymd = getStartDateYearMonthDay();
        return (ymd == null) ? null : new java.util.Date(ymd.getYear() - 1900, ymd.getMonthOfYear() - 1, ymd.getDayOfMonth());
    }

    @Deprecated
    public void setStartDate(java.util.Date date) {
        if (date == null) {
            setStartDateYearMonthDay(null);
        } else {
            setStartDateYearMonthDay(org.joda.time.YearMonthDay.fromDateFields(date));
        }
    }

    @Deprecated
    public boolean hasGratuityValues() {
        return getGratuityValues() != null;
    }

    @Deprecated
    public boolean hasValue() {
        return getValue() != null;
    }

    @Deprecated
    public boolean hasDescription() {
        return getDescription() != null;
    }

    @Deprecated
    public boolean hasBennu() {
        return getRootDomainObject() != null;
    }

    @Deprecated
    public boolean hasEndDateYearMonthDay() {
        return getEndDateYearMonthDay() != null;
    }

    @Deprecated
    public boolean hasStartDateYearMonthDay() {
        return getStartDateYearMonthDay() != null;
    }

}
