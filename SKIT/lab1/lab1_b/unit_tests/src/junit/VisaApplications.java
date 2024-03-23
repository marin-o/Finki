package junit;

import java.util.ArrayList;
import java.util.List;

public class VisaApplications {
    /**
     * if any of the lists is null throw NullPointerException,
     * else return a non-null list of applicants that have applied for a UK Visa, but not for a US visa
     * else if there are no such applicants return null
     * @param ukVisaApplications ids of applicants for uk visa
     * @param usaVisaApplications ids of applicants for us visa
     */
    public static List<String> applicantsForUkVisaOnly(List<String> ukVisaApplications, List<String> usaVisaApplications){
        if(ukVisaApplications == null || usaVisaApplications == null)
            throw new NullPointerException();

        List<String> onlyUk = new ArrayList<>();

        for (String applicant :
                ukVisaApplications) {
            if (!usaVisaApplications.contains(applicant))
                onlyUk.add(applicant);
        }

        return onlyUk.isEmpty() ? null : onlyUk;
    }
}
