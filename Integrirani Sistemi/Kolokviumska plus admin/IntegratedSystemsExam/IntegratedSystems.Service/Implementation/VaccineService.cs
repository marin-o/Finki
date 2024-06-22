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
    public class VaccineService : IVaccineService
    {
        private readonly IRepository<Vaccine> _vaccineRepository;

        public VaccineService(IRepository<Vaccine> vaccineService)
        {
            _vaccineRepository = vaccineService;
        }

        public void CreateNewVaccine(Vaccine p)
        {
            _vaccineRepository.Insert(p);
        }

        public void DeleteVaccine(Guid id)
        {
            var product = _vaccineRepository.Get(id);
            _vaccineRepository.Delete(product);
        }

        public List<Vaccine> GetAllVaccines()
        {
            return _vaccineRepository.GetAll().ToList();
        }

        public Vaccine GetDetailsForVaccine(Guid? id)
        {
            var product = _vaccineRepository.Get(id);
            return product;
        }

        public void UpdateExistingVaccine(Vaccine p)
        {
            _vaccineRepository.Update(p);
        }
    }
}
