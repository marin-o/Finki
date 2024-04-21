using IntegratedSystems.Domain.Domain_Models;


namespace IntegratedSystems.Service.Interface
{
    public interface IPatientService
    {
        public List<Patient> GetPatients();
        public Patient GetPatientById(Guid? id);
        public Patient CreateNewPatient(Patient patient);
        public Patient UpdatePatient(Patient patient);
        public Patient DeletePatient(Guid id);

    }
}
