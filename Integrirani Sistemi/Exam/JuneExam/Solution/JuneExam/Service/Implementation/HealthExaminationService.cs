using Domain.Domain_Models;
using Repository.Implementation;
using Repository.Interface;
using Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Implementation
{
    public class HealthExaminationService : IHealthExaminationService
    {
        private readonly IRepository<HealthExamination> _repository;
        public HealthExaminationService(IRepository<HealthExamination> repository)
        {
            _repository = repository;
        }

        public void CreateNewHealthExamination(HealthExamination p)
        {
            _repository.Insert(p);
        }

        public void DeleteHealthExamination(Guid id)
        {
            _repository.Delete(_repository.Get(id));
        }

        public List<HealthExamination> GetAllHealthExaminations()
        {
            return _repository.GetAll().ToList();
        }

        public HealthExamination GetDetailsForHealthExamination(Guid? id)
        {
            return _repository.Get(id);
        }

        public void UpdateExistingHealthExamination(HealthExamination p)
        {
            _repository.Update(p);
        }
    }
}
