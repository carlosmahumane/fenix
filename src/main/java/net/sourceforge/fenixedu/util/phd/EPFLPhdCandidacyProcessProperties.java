package net.sourceforge.fenixedu.util.phd;

import net.sourceforge.fenixedu.domain.phd.candidacy.PhdProgramPublicCandidacyHashCode;

import org.fenixedu.commons.configuration.ConfigurationInvocationHandler;
import org.fenixedu.commons.configuration.ConfigurationManager;
import org.fenixedu.commons.configuration.ConfigurationProperty;

public class EPFLPhdCandidacyProcessProperties {
    @ConfigurationManager(description = "EPFL Properties")
    public interface ConfigurationProperties {
        @ConfigurationProperty(key = "phd.epfl.public.candidacy.access.link")
        public String getPublicCandidacyAccessLink();

        @ConfigurationProperty(key = "phd.epfl.public.candidacy.submission.link")
        public String getPublicCandidacySubmissionLink();

        @ConfigurationProperty(key = "phd.epfl.public.candidacy.referee.form.link")
        public String getPublicCandidacyRefereeFormLink();

        @ConfigurationProperty(key = "phd.public.external.access.link")
        public String getPhdExternalAccessLink();
    }

    public static ConfigurationProperties getConfiguration() {
        return ConfigurationInvocationHandler.getConfiguration(ConfigurationProperties.class);
    }

    public static String getPublicCandidacyAccessLink(PhdProgramPublicCandidacyHashCode candidacyProcessHashCode) {
        return String.format("%s?hash=%s", EPFLPhdCandidacyProcessProperties.getConfiguration().getPublicCandidacyAccessLink(),
                candidacyProcessHashCode.getValue());
    }
}
