using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Domain_Models
{
    public class Company : BaseEntity
    {
        public string? CompanyName { get; set; }
        public string? CompanyAddress { get; set;}
        public virtual ICollection<Employee>? ListOfEmployees { get; set; }
    }
}
