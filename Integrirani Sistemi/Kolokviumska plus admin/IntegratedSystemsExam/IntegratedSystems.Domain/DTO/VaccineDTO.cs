using IntegratedSystems.Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Domain.DTO
{
    public class VaccineDTO
    {
        public Guid Id { get; set; }
        public List<Patient>? Patients { get; set; }
        public List<string>? Manufacturers { get; set; }
        public Guid? Certificate { get; set; }
        public DateTime? DateTaken { get; set; }
        public Guid? PatientId { get; set; }
        public Guid? VaccinationCenter { get; set; }
        public string? Manufacturer{ get; set; }
    }
}
