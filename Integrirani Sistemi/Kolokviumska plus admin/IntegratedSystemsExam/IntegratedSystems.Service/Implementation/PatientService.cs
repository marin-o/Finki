using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Repository.Interface;
using IntegratedSystems.Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Implementation
{
    public class PatientService : IPatientService
    {
        private readonly IRepository<Patient> _patientRepository;

        public PatientService(IRepository<Patient> patientService)
        {
            _patientRepository = patientService;
        }

        public void CreateNewPatient(Patient p)
        {
            _patientRepository.Insert(p);
        }

        public void DeletePatient(Guid id)
        {
            var product = _patientRepository.Get(id);
            _patientRepository.Delete(product);
        }

        public List<Patient> GetAllPatients()
        {
            return _patientRepository.GetAll().ToList();
        }

        public Patient GetDetailsForPatient(Guid? id)
        {
            var product = _patientRepository.Get(id);
            return product;
        }

        public void UpdateExistingPatient(Patient p)
        {
            _patientRepository.Update(p);
        }
    }
}
