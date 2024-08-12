using Domain.Domain_Models;
using Domain.DTO;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Service.Interface;

namespace Web.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AdminController : ControllerBase
    {
        private readonly IPolyclinicService _polyclinicService;
        private readonly IHealthExaminationService _healthExaminationService;
        private readonly IEmployeeService _employeeService;

        public AdminController(IEmployeeService s, IPolyclinicService polyclinicService, IHealthExaminationService healthExaminationService)
        {
            _polyclinicService = polyclinicService;
            _healthExaminationService = healthExaminationService;
            _employeeService = s;
        }

        [HttpGet("[action]")]
        public List<Polyclinic> GetAllPolyclinics()
        {
            return _polyclinicService.GetAllPolyclinics();
        }

        [HttpGet("[action]")]
        public List<HealthExamination> GetAllHealthExaminations()
        {
            return _healthExaminationService.GetAllHealthExaminations();
        }

        [HttpPost("[action]")]
        public HealthExamination GetDetailsForHealthExamination(BaseEntity id)
        {
            return _healthExaminationService.GetDetailsForHealthExamination(id.Id);
        }

        [HttpPost("[action]")]
        public bool ImportExaminations(List<HealthExaminationDTO> model)
        {
            bool status = true;
            var examinations = _healthExaminationService.GetAllHealthExaminations();
            foreach (var item in model)
            {
                var userCheck = examinations.Where(e => e.EmployeeId == item.EmployeeId).FirstOrDefault();

                if (userCheck == null)
                {
                    var user = new HealthExamination
                    {
                        EmployeeId = (Guid)item.EmployeeId,
                        PolyclinicId = (Guid)item.PolyclinicId,
                        Employee = _employeeService.GetDetailsForEmployee(item.EmployeeId),
                        Polyclinic = _polyclinicService.GetDetailsForPolyclinic(item.PolyclinicId),
                        Description = item.Description,
                        Id = Guid.NewGuid()

                    };

                    _healthExaminationService.CreateNewHealthExamination(user);

                    var result = user != null;
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
