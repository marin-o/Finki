using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Domain.Domain_Models;
using Repository;
using Service.Interface;
using Domain.DTO;

namespace Web.Controllers
{
    public class EmployeesController : Controller
    {
        private readonly ApplicationDbContext _context;
        private readonly IEmployeeService employeeService;
        private readonly ICompanyService companyService;
        private readonly IHealthExaminationService healthExaminationService;
        private readonly IPolyclinicService polyclinicService;

        public EmployeesController(ApplicationDbContext context, IEmployeeService eService, ICompanyService companyService, IHealthExaminationService healthExaminationService, IPolyclinicService pService)
        {
            _context = context;
            employeeService = eService;
            this.companyService = companyService;
            this.healthExaminationService = healthExaminationService;
            polyclinicService = pService;
        }

        public IActionResult AddAppointment(Guid? id)
        {
            var employee = employeeService.GetDetailsForEmployee(id);

            var exam = new HealthExaminationDTO
            {
                EmployeeId = employee.Id
            };

            ViewData["Clinics"] = polyclinicService.GetAllPolyclinics();
            var c = companyService.GetAllCompanies().Where(c => c.ListOfEmployees.Any(l => l.Id == exam.EmployeeId)).FirstOrDefault();
            ViewData["CompanyName"] = c.CompanyName;

            return View(exam);
        }

        [HttpPost]
        public IActionResult AddExamination(HealthExaminationDTO dto)
        {

            var employee = employeeService.GetDetailsForEmployee(dto.EmployeeId);
            var clinic = polyclinicService.GetDetailsForPolyclinic(dto.PolyclinicId);

            if (clinic.AvailableSlots == 0)
            {
                return RedirectToAction("FullClinic");
            }

            var exam = new HealthExamination
            {
                Id = Guid.NewGuid(),
                EmployeeId = (Guid) dto.EmployeeId,
                Description = dto.Description,
                Employee = employee,
                DateTaken = DateTime.Now,
                PolyclinicId = (Guid)dto.PolyclinicId,
                Polyclinic = clinic
            };

            clinic.AvailableSlots--;

            healthExaminationService.CreateNewHealthExamination(exam);

            return RedirectToAction("Index");
        }

        public IActionResult FullClinic()
        {
            return View();
        }

        // GET: Employees
        public IActionResult Index()
        {
            var employees = employeeService.GetAllEmployees();
            foreach(var e in employees)
            {
                var c = companyService.GetAllCompanies().Where(c => c.ListOfEmployees.Any(l => l.Id == e.Id)).FirstOrDefault();
                e.Company = c;
            }
            return View(employees);
        }

        // GET: Employees/Details/5
        public IActionResult Details(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var employee = employeeService.GetDetailsForEmployee(id);
            if (employee == null)
            {
                return NotFound();
            }
            var c = companyService.GetAllCompanies().Where(c => c.ListOfEmployees.Any(l => l.Id == employee.Id)).FirstOrDefault();
            employee.Company = c;

            ViewData["Exams"] = healthExaminationService.GetAllHealthExaminations().Where(e => e.Employee.Id == employee.Id).ToList();

            return View(employee);
        }

        // GET: Employees/Create
        public IActionResult Create()
        {
            ViewData["CompanyId"] = new SelectList(_context.Companies, "Id", "Id");
            return View();
        }

        // POST: Employees/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Create([Bind("FullName,DateOfBirth,Title,CompanyId,Id")] Employee employee)
        {
            if (ModelState.IsValid)
            {
                employee.Id = Guid.NewGuid();
                employeeService.CreateNewEmployee(employee);
                return RedirectToAction(nameof(Index));
            }
            ViewData["CompanyId"] = new SelectList(_context.Companies, "Id", "Id", employee.CompanyId);
            return View(employee);
        }

        // GET: Employees/Edit/5
        public IActionResult Edit(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var employee = employeeService.GetDetailsForEmployee(id);
            if (employee == null)
            {
                return NotFound();
            }
            ViewData["CompanyId"] = new SelectList(_context.Companies, "Id", "Id", employee.CompanyId);
            return View(employee);
        }

        // POST: Employees/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Edit(Guid id, [Bind("FullName,DateOfBirth,Title,CompanyId,Id")] Employee employee)
        {
            if (id != employee.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    employeeService.UpdateExistingEmployee(employee);
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!EmployeeExists(employee.Id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            ViewData["CompanyId"] = new SelectList(_context.Companies, "Id", "Id", employee.CompanyId);
            return View(employee);
        }

        // GET: Employees/Delete/5
        public IActionResult Delete(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }
            var employee = employeeService.GetDetailsForEmployee(id);

            if (employee == null)
            {
                return NotFound();
            }

            return View(employee);
        }

        // POST: Employees/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public IActionResult DeleteConfirmed(Guid id)
        {
            var employee = employeeService.GetDetailsForEmployee(id);
            if (employee != null)
            {
                employeeService.DeleteEmployee(id);
            }

            
            return RedirectToAction(nameof(Index));
        }

        private bool EmployeeExists(Guid id)
        {
            return _context.Employees.Any(e => e.Id == id);
        }
    }
}
