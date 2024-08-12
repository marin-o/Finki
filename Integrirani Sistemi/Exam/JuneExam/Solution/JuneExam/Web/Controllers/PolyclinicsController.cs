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

namespace Web.Controllers
{
    public class PolyclinicsController : Controller
    {
        // https://localhost:44305/
        private readonly ApplicationDbContext _context;
        private readonly IPolyclinicService polyclinicService;
        private readonly IHealthExaminationService healthService;

        public PolyclinicsController(ApplicationDbContext context, IPolyclinicService pService, IHealthExaminationService h )
        {
            _context = context;
            polyclinicService = pService;
            healthService = h;
        }

        // GET: Polyclinics
        public IActionResult Index()
        {
            return View(polyclinicService.GetAllPolyclinics());
        }

        // GET: Polyclinics/Details/5
        public IActionResult Details(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var polyclinic = polyclinicService.GetDetailsForPolyclinic(id);
            if (polyclinic == null)
            {
                return NotFound();
            }

            var zdravje = healthService.GetAllHealthExaminations().Where(h => h.PolyclinicId == id).ToList();

            ViewData["Exams"] = zdravje;

            return View(polyclinic);
        }

        // GET: Polyclinics/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Polyclinics/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Create([Bind("PolyclinicName,PolyclinicAddress,Reputation,AvailableSlots,Id")] Polyclinic polyclinic)
        {
            polyclinic.HealthExaminations = new List<HealthExamination>();

                polyclinic.Id = Guid.NewGuid();
                polyclinicService.CreateNewPolyclinic(polyclinic);
                return RedirectToAction(nameof(Index));
            
            return View(polyclinic);
        }

        // GET: Polyclinics/Edit/5
        public IActionResult Edit(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var polyclinic = polyclinicService.GetDetailsForPolyclinic(id);
            if (polyclinic == null)
            {
                return NotFound();
            }
            return View(polyclinic);
        }

        // POST: Polyclinics/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Edit(Guid id, [Bind("PolyclinicName,PolyclinicAddress,Reputation,AvailableSlots,Id")] Polyclinic polyclinic)
        {
            if (id != polyclinic.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    polyclinicService.UpdateExistingPolyclinic(polyclinic);
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!PolyclinicExists(polyclinic.Id))
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
            return View(polyclinic);
        }

        // GET: Polyclinics/Delete/5
        public IActionResult Delete(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var polyclinic = polyclinicService.GetDetailsForPolyclinic(id);
            if (polyclinic == null)
            {
                return NotFound();
            }

            return View(polyclinic);
        }

        // POST: Polyclinics/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public IActionResult DeleteConfirmed(Guid id)
        {
            var polyclinic = polyclinicService.GetDetailsForPolyclinic(id);
            if (polyclinic != null)
            {
                _context.Polyclinics.Remove(polyclinic);
            }

            return RedirectToAction(nameof(Index));
        }

        private bool PolyclinicExists(Guid id)
        {
            return polyclinicService.GetAllPolyclinics().Exists(e  => e.Id == id);
        }
    }
}
