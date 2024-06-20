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
        private readonly IRepository<Patient> _repository;

        public PatientService(IRepository<Patient> repository)
        {
            _repository = repository;
        }

        public Patient Delete(Patient patient)
        {
            return _repository.Delete(patient);
        }

        public Patient Get(Guid? id)
        {
            return _repository.Get(id);
        }

        public List<Patient> GetAll()
        {
            return _repository.GetAll().ToList();
        }

        public Patient Insert(Patient patient)
        {
            return _repository.Insert(patient);
        }

        public List<Patient> InsertMany(List<Patient> patients)
        {
            return _repository.InsertMany(patients);
        }

        public Patient Update(Patient patient)
        {
            return _repository.Update(patient);
        }
    }
}
