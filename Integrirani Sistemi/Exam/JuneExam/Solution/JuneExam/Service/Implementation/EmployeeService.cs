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
    public class EmployeeService : IEmployeeService
    {
        private readonly IRepository<Employee> _repository;
        public EmployeeService(IRepository<Employee> repository)
        {
            _repository = repository;
        }
        public void CreateNewEmployee(Employee p)
        {
            _repository.Insert(p);
        }

        public void DeleteEmployee(Guid id)
        {
            _repository.Delete(_repository.Get(id));
        }

        public List<Employee> GetAllEmployees()
        {
            return _repository.GetAll().ToList();
        }

        public Employee GetDetailsForEmployee(Guid? id)
        {
            return _repository.Get(id);
        }

        public void UpdateExistingEmployee(Employee p)
        {
            _repository.Update(p);
        }
    }
}
