using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Domain.DTO;
using IntegratedSystems.Repository.Interface;
using IntegratedSystems.Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Implementation
{
    public class VaccineService : IVaccineService
    {
        private readonly IRepository<Vaccine> _repository;
        private readonly IRepository<VaccinationCenter> _centerRepository;
        private readonly IRepository<Patient> _patientRepository;

        public VaccineService(IRepository<Vaccine> repository, IRepository<VaccinationCenter> repository1, IRepository<Patient> repository2)
        {
            _repository = repository;
            _centerRepository = repository1;
            _patientRepository = repository2;
        }

        public Vaccine Delete(Vaccine vaccine)
        {
            return _repository.Delete(vaccine);
        }

        public Vaccine Get(Guid? id)
        {
            return _repository.Get(id);
        }

        public List<Vaccine> GetAll()
        {
            return _repository.GetAll().ToList();
        }

        public Vaccine Insert(Vaccine vaccine)
        {
            return _repository.Insert(vaccine);
        }

        public List<Vaccine> InsertMany(List<Vaccine> vaccines)
        {
            return _repository.InsertMany(vaccines);
        }

        public Vaccine Update(Vaccine vaccine)
        {
            return _repository.Update(vaccine);
        }

        public List<Vaccine> GetVaccinesCenter(Guid centerId)
        {
            return _repository.GetAll().Where(x => x.VaccinationCenter ==  centerId).ToList();
        }

        public Vaccine AddVaccineToVaccinationCenter(VaccineDTO dto)
        {
            var patient = _patientRepository.Get(dto.PatientIdFor);
            var center = _centerRepository.Get(dto.VaccinationCenter);

            if (center == null || patient == null || center.MaxCapacity <= 0)
            {
                return null;
            }

            Vaccine model = new Vaccine()
            {
                Id = Guid.NewGuid(),
                Manufacturer = dto.Manufacturer,
                Certificate = Guid.NewGuid(),
                DateTaken = DateTime.Now,
                PatientId = (Guid)dto.PatientIdFor,
                PatientFor = patient,
                VaccinationCenter = center.Id,
                Center = center

            };

            center.MaxCapacity--;
            _centerRepository.Update(center);
            _repository.Insert(model);

            return model;
        }
    }
}
