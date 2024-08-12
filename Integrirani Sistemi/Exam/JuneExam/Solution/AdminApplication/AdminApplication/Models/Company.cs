using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AdminApplication.Models
{
    public class Company 
    {
        [Key]
        public Guid Id { get; set; }
        public string? CompanyName { get; set; }
        public string? CompanyAddress { get; set; }
        public virtual ICollection<Employee>? ListOfEmployees { get; set; }
    }
}
