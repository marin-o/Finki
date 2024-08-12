using Domain.Domain_Models;
using Repository.Interface;
using Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Implementation
{
    public class PolyclinicService : IPolyclinicService
    {

        private readonly IRepository<Polyclinic> _repository;

        public PolyclinicService(IRepository<Polyclinic> repository)
        {
            _repository = repository;
        }

        public void CreateNewPolyclinic(Polyclinic p)
        {
            _repository.Insert(p);
        }

        public void DeletePolyclinic(Guid id)
        {
            _repository.Delete(_repository.Get(id));
        }

        public List<Polyclinic> GetAllPolyclinics()
        {
            return _repository.GetAll().ToList();
        }

        public Polyclinic GetDetailsForPolyclinic(Guid? id)
        {
            return _repository.Get(id);
        }

        public void UpdateExistingPolyclinic(Polyclinic p)
        {
            _repository.Update(p);
        }
    }
}
