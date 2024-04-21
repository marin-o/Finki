using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Domain.DTO;
using IntegratedSystems.Repository.Interface;
using IntegratedSystems.Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Implementation {
    public class VaccineServiceImpl : IVaccineService {
        private readonly IRepository<VaccinationCenter> centerRepository;

        private readonly IRepository<Patient> patientRepository;
        private readonly IRepository<Vaccine> vaccineRepository;

        public VaccineServiceImpl(IRepository<VaccinationCenter> centerRepository, IRepository<Patient> patientRepository, IRepository<Vaccine> vaccineRepository) {
            this.centerRepository = centerRepository;
            this.patientRepository = patientRepository;
            this.vaccineRepository = vaccineRepository;
        }

        public void CreateNew(VaccineDTO dto) {
            VaccinationCenter center = centerRepository.Get(dto.vaccCenterId);
            Patient patient = patientRepository.Get(dto.patientId);
            Vaccine vaccine = new Vaccine {
                Id = Guid.NewGuid(),
                Manufacturer = dto.manufacturer,
                DateTaken = dto.vaccinationDate,
                PatientId = dto.patientId,
                PatientFor = patient,
                VaccinationCenter = dto.vaccCenterId,
                Center = center
            };
            vaccine.Certificate = vaccine.Id;
            vaccineRepository.Insert(vaccine);
        }

        public List<Vaccine> GetVaccinesForCenter(Guid? id) {
            return vaccineRepository.GetAll().Where(v => v.VaccinationCenter == id).ToList();
        }

        public List<Vaccine> GetVaccinesForPatient(Guid? id) {
            return vaccineRepository.GetAll().Where(p => p.PatientId == id).ToList();
        }
    }
}
