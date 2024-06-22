using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VezbanjeAdminApp.Models
{
    public class VaccinationCenter
    {
        [Key]
        public Guid Id { get; set; }
        public string? Name { get; set; }
        public string? Address { get; set; }
        public int MaxCapacity { get; set; }
        public virtual ICollection<Vaccine>? Vaccines { get; set; }
    }
}
