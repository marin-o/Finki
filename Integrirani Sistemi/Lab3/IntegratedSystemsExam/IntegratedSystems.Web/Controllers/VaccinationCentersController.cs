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
using Microsoft.Extensions.FileSystemGlobbing.Internal.PatternContexts;
using IntegratedSystems.Domain.DTO;

namespace IntegratedSystems.Web.Controllers
{
    public class VaccinationCentersController : Controller
    {
        private readonly IPatientService patientService;
        private readonly IVaccinationCenterService vaccinationCenterService;
        private readonly IVaccineService vaccineService;

        public VaccinationCentersController(IPatientService patientService, IVaccinationCenterService vaccinationCenterService, IVaccineService vaccineService) {
            this.patientService = patientService;
            this.vaccinationCenterService = vaccinationCenterService;
            this.vaccineService = vaccineService;
        }

        // GET: VaccinationCenters
        public async Task<IActionResult> Index()
        {
            return View(vaccinationCenterService.GetVaccinationCenters());
        }

        // GET: VaccinationCenters/Details/5
        public async Task<IActionResult> Details(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var vaccinationCenter = vaccinationCenterService.GetVaccinationCenterById(id);
            CenterDetailsDTO dto = new CenterDetailsDTO();
            dto.center = vaccinationCenter;
            dto.vaccines = vaccineService.GetVaccinesForCenter(id);
            if (vaccinationCenter == null)
            {
                return NotFound();
            }

            return View(dto);
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
        public async Task<IActionResult> Create([Bind("Name,Address,MaxCapacity,Id")] VaccinationCenter vaccinationCenter)
        {
            if (ModelState.IsValid)
            {
                vaccinationCenter.Id = Guid.NewGuid();
                vaccinationCenterService.CreateNewVaccinationCenter(vaccinationCenter);
                return RedirectToAction(nameof(Index));
            }
            return View(vaccinationCenter);
        }

        // GET: VaccinationCenters/Edit/5
        public async Task<IActionResult> Edit(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var vaccinationCenter = vaccinationCenterService.GetVaccinationCenterById(id);
            if(vaccinationCenter == null)
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
        public async Task<IActionResult> Edit(Guid id, [Bind("Name,Address,MaxCapacity,Id")] VaccinationCenter vaccinationCenter)
        {
            if (id != vaccinationCenter.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    vaccinationCenterService.UpdateVaccinationCenter(vaccinationCenter);
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
        public async Task<IActionResult> Delete(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var vaccinationCenter = vaccinationCenterService.GetVaccinationCenterById(id);
            if(vaccinationCenter == null)
            {
                return NotFound();
            }

            return View(vaccinationCenter);
        }

        // POST: VaccinationCenters/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(Guid id)
        {
            vaccinationCenterService.DeleteVaccinationCenter(id);
            return RedirectToAction(nameof(Index));
        }


        public async Task<IActionResult> VaccinePatient(Guid id) {

            VaccinationCenter center = vaccinationCenterService.GetVaccinationCenterById(id);
            if(center.MaxCapacity == vaccineService.GetVaccinesForCenter(id).Count) {
                return RedirectToAction(nameof(MaxCapacityReached));
            }

            VaccineDTO dto = new VaccineDTO();

            dto.patients = patientService.GetPatients();
            dto.manufacturers = new List<string>(new string[] { "Pfizer", "J&J", "Sputnik" });
            dto.vaccCenterId = id;

            return View(dto);
        }

        

        [HttpPost]
        public async Task<IActionResult> VaccinePatient([Bind("manufacturer,patientId,vaccinationDate,vaccCenterId")] VaccineDTO dto) {
            vaccineService.CreateNew(dto);
            return RedirectToAction("Details", new {id = dto.vaccCenterId});
        }
        public async Task<IActionResult> MaxCapacityReached() {
            return View();
        }

        private bool VaccinationCenterExists(Guid id)
        {
            return vaccinationCenterService.GetVaccinationCenters().Any(e => e.Id == id);
        }
    }
}
