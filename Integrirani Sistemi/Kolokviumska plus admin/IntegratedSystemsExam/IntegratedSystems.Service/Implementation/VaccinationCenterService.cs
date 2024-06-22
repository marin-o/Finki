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
    public class VaccinationCenterService : IVaccinationCenterService
    {
        private readonly IRepository<VaccinationCenter> _vaccCenterRepository;

        public VaccinationCenterService(IRepository<VaccinationCenter> patientService)
        {
            _vaccCenterRepository = patientService;
        }

        public void CreateNewCenter(VaccinationCenter p)
        {
            _vaccCenterRepository.Insert(p);
        }

        public void DeleteCenter(Guid id)
        {
            var center = _vaccCenterRepository.Get(id);
            _vaccCenterRepository.Delete(center);
        }

        public List<VaccinationCenter> GetAllCenters()
        {
            return _vaccCenterRepository.GetAll().ToList();
        }

        public VaccinationCenter GetDetailsForCenter(Guid? id)
        {
            var center = _vaccCenterRepository.Get(id);
            return center;
        }

        public void UpdateExistingCenter(VaccinationCenter p)
        {
            _vaccCenterRepository.Update(p);
        }
    }
}
