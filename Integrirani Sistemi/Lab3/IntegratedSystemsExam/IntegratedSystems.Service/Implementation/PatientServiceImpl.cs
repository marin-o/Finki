using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Repository.Interface;
using IntegratedSystems.Service.Interface;

namespace IntegratedSystems.Service.Implementation
{
    public class PatientServiceImpl : IPatientService
    {

        private readonly IRepository<Patient> patientRepository;

        public PatientServiceImpl(IRepository<Patient> patientRepository)
        {
            this.patientRepository = patientRepository;
        }

        public Patient CreateNewPatient(Patient patient)
        {
            return patientRepository.Insert(patient);

        }

        public Patient DeletePatient(Guid id)
        {
            var patient = this.GetPatientById(id);
            return patientRepository.Delete(patient);
        }

        public Patient GetPatientById(Guid? id)
        {
            return patientRepository.Get(id);
        }

        public List<Patient> GetPatients()
        {
            return patientRepository.GetAll().ToList();
        }

        public Patient UpdatePatient(Patient patient)
        {
            return patientRepository.Update(patient);
        }
    }
}
