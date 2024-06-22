using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Repository;
using IntegratedSystems.Service.Interface;
using IntegratedSystems.Domain.DTO;

namespace IntegratedSystems.Web.Controllers
{
    public class VaccinationCentersController : Controller
    {
        //private readonly ApplicationDbContext _context;
        private readonly IVaccinationCenterService _centerService;
        private readonly IVaccineService _vaccineService;
        private readonly IPatientService _patientService;

        public VaccinationCentersController(IPatientService patientService, IVaccineService vaccineService, IVaccinationCenterService centerService/*ApplicationDbContext context*/)
        {

            //_context = context;
            _centerService = centerService;
            _vaccineService = vaccineService;
            _patientService = patientService;
        }

        // GET: VaccinationCenters
        public IActionResult Index()
        {
            return View(_centerService.GetAllCenters());
        }

        // GET: VaccinationCenters/Details/5
        public IActionResult Details(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var vaccinationCenter = _centerService.GetDetailsForCenter(id);
            if (vaccinationCenter == null)
            {
                return NotFound();
            }

            ViewData["Vaccines"] = _vaccineService.GetAllVaccines().Where(v => v.Center.Id == id).ToList();

            return View(vaccinationCenter);
        }

        // GET: VaccinationCenters/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: VaccinationCenters/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Create([Bind("Name,Address,MaxCapacity,Id")] VaccinationCenter vaccinationCenter)
        {
            if (ModelState.IsValid)
            {
                vaccinationCenter.Id = Guid.NewGuid();
                _centerService.CreateNewCenter(vaccinationCenter);
                return RedirectToAction(nameof(Index));
            }
            return View(vaccinationCenter);
        }

        // GET: VaccinationCenters/Edit/5
        public IActionResult Edit(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var vaccinationCenter = _centerService.GetDetailsForCenter(id);
            if (vaccinationCenter == null)
            {
                return NotFound();
            }
            return View(vaccinationCenter);
        }

        // POST: VaccinationCenters/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Edit(Guid id, [Bind("Name,Address,MaxCapacity,Id")] VaccinationCenter vaccinationCenter)
        {
            if (id != vaccinationCenter.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _centerService.UpdateExistingCenter(vaccinationCenter);
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!VaccinationCenterExists(vaccinationCenter.Id))
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
            return View(vaccinationCenter);
        }

        // GET: VaccinationCenters/Delete/5
        public IActionResult Delete(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var vaccinationCenter = _centerService.GetDetailsForCenter(id);
            if (vaccinationCenter == null)
            {
                return NotFound();
            }

            return View(vaccinationCenter);
        }

        // POST: VaccinationCenters/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public IActionResult DeleteConfirmed(Guid id)
        {
            var vaccinationCenter = _centerService.GetDetailsForCenter(id);
            if (vaccinationCenter != null)
            {
                _centerService.DeleteCenter(vaccinationCenter.Id);
            }

            return RedirectToAction(nameof(Index));
        }

        [HttpGet]
        public IActionResult AddVaccinedPatient(Guid? id)
        {
            var dto = new VaccineDTO()
            {
                Id = Guid.NewGuid(),
                Patients = _patientService.GetAllPatients().ToList(),
                Manufacturers = new List<string>() { "Pfizer", "Moderna", "AstraZeneca", "Johnson & Johnson" },
                Certificate = Guid.NewGuid(),
                VaccinationCenter = id,
            };

            return View(dto);
        }
        [HttpPost]
        public IActionResult AddVaccinedPatient([Bind("Id,Manufacturer,PatientId,DateTaken,Certificate, VaccinationCenter")]Vaccine vaccine)
        {
            var center = _centerService.GetDetailsForCenter(vaccine.VaccinationCenter);
            if (center.MaxCapacity == 0)
            {
                return RedirectToAction(nameof(MaxCapacity));
            }
            var patient = _patientService.GetDetailsForPatient(vaccine.PatientId);
            //vaccine.PatientFor = patient;
            //patient.VaccinationSchedule.Add(vaccine);
            center.MaxCapacity--;
            vaccine.Center = center;
            _vaccineService.CreateNewVaccine(vaccine);
            return RedirectToAction(nameof(Index));
        }
        [HttpGet]
        public IActionResult MaxCapacity()
        {
            return View();
        }

        private bool VaccinationCenterExists(Guid id)
        {
            return _centerService.GetAllCenters().Any(e => e.Id == id);
        }
    }
}
