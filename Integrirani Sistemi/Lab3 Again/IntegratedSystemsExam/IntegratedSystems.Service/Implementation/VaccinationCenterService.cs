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
        private readonly IRepository<VaccinationCenter> _repository;

        public VaccinationCenterService(IRepository<VaccinationCenter> repository)
        {
            _repository = repository;
        }

        public VaccinationCenter Delete(VaccinationCenter center)
        {
            return _repository.Delete(center);
        }

        public VaccinationCenter Get(Guid? id)
        {
            return _repository.Get(id);
        }

        public List<VaccinationCenter> GetAll()
        {
            return _repository.GetAll().ToList();
        }

        public VaccinationCenter Insert(VaccinationCenter center)
        {
            return _repository.Insert(center);
        }

        public List<VaccinationCenter> InsertMany(List<VaccinationCenter> centers)
        {
            return _repository.InsertMany(centers);
        }

        public VaccinationCenter Update(VaccinationCenter center)
        {
            return _repository.Update(center);
        }
    }
}
