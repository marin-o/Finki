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
    public class CompanyService : ICompanyService
    {
        private readonly IRepository<Company> _repository;
        public CompanyService(IRepository<Company> repository)
        {
            _repository = repository;
        }

        public void CreateNewCompany(Company p)
        {
            _repository.Insert(p);
        }

        public void DeleteCompany(Guid id)
        {
            _repository.Delete(_repository.Get(id));
        }

        public List<Company> GetAllCompanies()
        {
            return _repository.GetAll().ToList();
        }

        public Company GetDetailsForCompany(Guid? id)
        {
            return _repository.Get(id);
        }

        public void UpdateExistingCompany(Company p)
        {
            _repository.Update(p);
        }
    }
}
