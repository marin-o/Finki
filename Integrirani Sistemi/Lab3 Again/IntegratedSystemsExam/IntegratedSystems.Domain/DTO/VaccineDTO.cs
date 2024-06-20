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
        public List<Patient>? AllPatients { get; set; }
        public List<string>? AllManufacturers { get; set; }
        public Guid? PatientIdFor {  get; set; }
        public string? Manufacturer {  get; set; }
        public Guid? VaccinationCenter {  get; set; }
    }
}
