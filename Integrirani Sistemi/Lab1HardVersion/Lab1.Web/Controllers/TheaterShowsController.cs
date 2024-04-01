using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Lab1.Web.Data;
using Lab1.Web.Models.Domain;
using Lab1.Web.Models.Identity;
using System.Security.Claims;

namespace Lab1.Web.Controllers
{
    public class TheaterShowsController : Controller
    {
        private readonly ApplicationDbContext _context;

        public TheaterShowsController(ApplicationDbContext context)
        {
            _context = context;
        }

        // GET: TheaterShows
        public async Task<IActionResult> Index()
        {
            return View(await _context.TheaterShows.ToListAsync());
        }

        // GET: TheaterShows/Details/5
        public async Task<IActionResult> Details(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var theaterShow = await _context.TheaterShows
                .FirstOrDefaultAsync(m => m.Id == id);
            if (theaterShow == null)
            {
                return NotFound();
            }

            return View(theaterShow);
        }

        // GET: TheaterShows/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: TheaterShows/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,Title,Description,ShowDate,Place,ImageUrl")] TheaterShow theaterShow)
        {
            if (ModelState.IsValid)
            {
                theaterShow.Id = Guid.NewGuid();
                _context.Add(theaterShow);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(theaterShow);
        }

        // GET: TheaterShows/Edit/5
        public async Task<IActionResult> Edit(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var theaterShow = await _context.TheaterShows.FindAsync(id);
            if (theaterShow == null)
            {
                return NotFound();
            }
            return View(theaterShow);
        }

        // POST: TheaterShows/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(Guid id, [Bind("Id,Title,Description,ShowDate,Place,ImageUrl")] TheaterShow theaterShow)
        {
            if (id != theaterShow.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(theaterShow);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!TheaterShowExists(theaterShow.Id))
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
            return View(theaterShow);
        }

        // GET: TheaterShows/Delete/5
        public async Task<IActionResult> Delete(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var theaterShow = await _context.TheaterShows
                .FirstOrDefaultAsync(m => m.Id == id);
            if (theaterShow == null)
            {
                return NotFound();
            }

            return View(theaterShow);
        }

        // POST: TheaterShows/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(Guid id)
        {
            var theaterShow = await _context.TheaterShows.FindAsync(id);
            if (theaterShow != null)
            {
                _context.TheaterShows.Remove(theaterShow);
            }

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        public async Task<IActionResult> BuyTicket(Guid? id)
        {
            if(id == null)
            {
                return NotFound();
            }

            var show = await _context.TheaterShows.FindAsync(id);
            var user = User.FindFirstValue(ClaimTypes.NameIdentifier);


            if (show == null || user == null) {
                return NotFound();
            }

            var loggedInUser = await _context.Users.FindAsync(user);

            Ticket ticket = new Ticket {
                Id = Guid.NewGuid(),
                TheaterShowId = show.Id,
                UserId = Guid.Parse(user),
                TheaterShow = show,
                Lab1User = loggedInUser,
                Price = -1
            };

            return View(ticket);
        }

        [HttpPost]
        public async Task<IActionResult> BuyTicketConfirmed(Ticket model) {
            var userId = User.FindFirstValue(ClaimTypes.NameIdentifier);
            var loggedInUser = await _context.Users.FindAsync(userId);
            var show = await _context.TheaterShows.FindAsync(model.TheaterShowId);

            model.TheaterShow = show;
            model.Lab1User = loggedInUser;

            _context.Tickets.Add(model);
            await _context.SaveChangesAsync();

            return View("Index", await _context.TheaterShows.ToListAsync());
        }

        private bool TheaterShowExists(Guid id){
            return _context.TheaterShows.Any(e => e.Id == id);
        }
    }
}
