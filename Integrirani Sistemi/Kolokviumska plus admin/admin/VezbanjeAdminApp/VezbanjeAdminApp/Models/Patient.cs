using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VezbanjeAdminApp.Models
{
    public class Patient
    {
        [Key]
        public Guid Id { get; set; }
        public string? Embg { get; set; }
        public string? FirstName { get; set; }
        public string? LastName { get; set; }
        public string? PhoneNumber { get; set; }
        public string? Email { get; set; }
        public ICollection<Vaccine>? VaccinationSchedule { get; set; }
    }
}
