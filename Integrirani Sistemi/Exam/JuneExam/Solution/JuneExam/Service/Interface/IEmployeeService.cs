using Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Interface
{
    public interface IEmployeeService
    {
        List<Employee> GetAllEmployees();
        Employee GetDetailsForEmployee(Guid? id);
        void CreateNewEmployee(Employee p);
        void UpdateExistingEmployee(Employee p);
        void DeleteEmployee(Guid id);
    }
}
