using Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Interface
{
    public interface ICompanyService
    {
        List<Company> GetAllCompanies();
        Company GetDetailsForCompany(Guid? id);
        void CreateNewCompany(Company p);
        void UpdateExistingCompany(Company p);
        void DeleteCompany(Guid id);
    }
}
