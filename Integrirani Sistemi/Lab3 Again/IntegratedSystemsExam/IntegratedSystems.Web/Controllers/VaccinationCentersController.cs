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
        private readonly IVaccinationCenterService vaccinationCenterService;
        private readonly IVaccineService vaccineService;
        private readonly IPatientService patientService;

        public VaccinationCentersController(IVaccinationCenterService centerService, IVaccineService vaccineService, IPatientService patientService)
        {
            vaccinationCenterService = centerService;
            this.vaccineService = vaccineService;
            this.patientService = patientService;
        }

        // GET: VaccinationCenters
        public IActionResult Index()
        {
            return View(vaccinationCenterService.GetAll());
        }

        // GET: VaccinationCenters/Details/5
        public IActionResult Details(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var vaccinationCenter = vaccinationCenterService.Get(id);
            if (vaccinationCenter == null)
            {
                return NotFound();
            }

            var vaccines = vaccineService.GetVaccinesCenter((Guid)id);

            ViewData["Vaccines"] = vaccines;

            return View(vaccinationCenter);
        }

        public IActionResult AddVaccine(Guid? id)
        {
            var dto = new VaccineDTO
            {
                AllPatients = patientService.GetAll(),
                AllManufacturers = new List<string> { "M1", "M2", "M3" },
                VaccinationCenter = id
            };

            return View(dto);

        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult AddVaccineToVaccinationCenter(VaccineDTO vaccine)
        {
            var result = vaccineService.AddVaccineToVaccinationCenter(vaccine);

            return result is null ? View("ErrorPage") : RedirectToAction(nameof(Index));
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
                vaccinationCenterService.Insert(vaccinationCenter);
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

            var vaccinationCenter = vaccinationCenterService.Get(id);
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
                    vaccinationCenterService.Update(vaccinationCenter);
                    
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

            var vaccinationCenter = vaccinationCenterService.Get(id);
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
            var vaccinationCenter = vaccinationCenterService.Get(id);
            if (vaccinationCenter != null)
            {
                vaccinationCenterService.Delete(vaccinationCenter);
            }

            return RedirectToAction(nameof(Index));
        }

        private bool VaccinationCenterExists(Guid id)
        {
            return vaccinationCenterService.GetAll().Any(e => e.Id == id);
        }
    }
}
