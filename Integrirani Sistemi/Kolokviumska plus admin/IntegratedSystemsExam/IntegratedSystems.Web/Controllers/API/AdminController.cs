using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Domain.DTO;
using IntegratedSystems.Repository.Interface;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;

namespace IntegratedSystems.Web.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class AdminController : ControllerBase
    {
        private readonly IVaccinationCenterRepository _vaccinationCenterRepository;
        private readonly IRepository<Patient> _patientRepository;

        public AdminController(IRepository<Patient> p, IVaccinationCenterRepository vaccinationCenterRepository)
        {
            _vaccinationCenterRepository = vaccinationCenterRepository;
            _patientRepository = p;
        }

        [HttpGet("GetAllCenters")]
        public List<VaccinationCenter> GetAllCenters()
        {
            return _vaccinationCenterRepository.GetAllCenters();
        }

        [HttpPost("[action]")]
        public VaccinationCenter GetDetails(BaseEntity id)
        {
            return _vaccinationCenterRepository.GetCenter(id);
        }

        [HttpPost("[action]")]
        public bool ImportAllPatients(List<PatientDTO> model)
        {
            var patients = _patientRepository.GetAll();

            bool status = true;

            foreach (var item in model)
            {
                var userCheck = patients.FirstOrDefault(x => x.Embg == item.Embg);

                if (userCheck == null)
                {
                    var user = new Patient
                    {
                        Id = Guid.NewGuid(),
                        Embg = item.Embg,
                        FirstName = item.FirstName,
                        LastName = item.LastName,
                        PhoneNumber = item.PhoneNumber,
                        Email = item.Email
                    };
                    var result = _patientRepository.Insert(user) != null;
                    status = status && result;
                }
                else
                {
                    continue;
                }
            }
            return status;
        }
    }
}
