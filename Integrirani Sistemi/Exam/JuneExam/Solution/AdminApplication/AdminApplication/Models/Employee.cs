using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AdminApplication.Models
{
    public class Employee
    {
        [Key]
        public Guid Id { get; set; }
        public string? FullName { get; set; }
        public DateTime? DateOfBirth { get; set; }
        public string? Title { get; set; }
        public Guid CompanyId { get; set; }
        public virtual Company? Company { get; set; }
        public virtual ICollection<HealthExamination>? HealthExaminations { get; set; }
    }
}
