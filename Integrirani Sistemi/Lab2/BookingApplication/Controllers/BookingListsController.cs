using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using BookingApplication.Data;
using BookingApplication.Models;
using System.Security.Claims;
using NuGet.Packaging;
using NuGet.Versioning;

namespace BookingApplication.Controllers
{
    public class BookingListsController : Controller
    {
        private readonly ApplicationDbContext _context;

        public BookingListsController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpPost]
        public IActionResult BookNow(int nights){
            var userId = User.FindFirstValue(ClaimTypes.NameIdentifier);

            if(userId == null)
                return NotFound();

            var loggedInUser = _context.Users.Where(u => u.Id == userId)
                .Include(u => u.Reservations)
                .Include(u=> u.BookingList)
                .FirstOrDefault();

            if (loggedInUser == null || loggedInUser.Reservations == null)
                return NotFound();

            var list = loggedInUser.BookingList;
            if(list == null) {

                list = new BookingList {
                    Id = Guid.NewGuid(),
                    OwnerId = userId,
                    Owner = loggedInUser,
                };
                _context.BookingLists.Add(list);
            }

            List<BookReservation> reservations = loggedInUser.Reservations
                .Select(r => new BookReservation {
                    Id = Guid.NewGuid(),
                    ReservationId = r.Id,
                    Reservation = r,
                    BookingListId = list.Id,
                    BookingList = list,
                    NumberOfNights = nights,
                }).ToList();

                
            _context.BookReservations.AddRange(reservations);
            loggedInUser.Reservations.Clear();

            _context.SaveChanges();
            

            return RedirectToAction("Index");

        }


        // GET: BookingLists
        public IActionResult Index()
        {
            var userId = User.FindFirstValue(ClaimTypes.NameIdentifier);

            if(userId == null)
                return NotFound();

            var loggedInUser =  _context.Users.Where(u => u.Id == userId)
                .Include(u => u.BookingList)
                .FirstOrDefault();

            if(loggedInUser == null || loggedInUser.BookingList == null)
                return NotFound();

            var list =  _context.BookingLists.Where(b => b.OwnerId == userId)
                .Include(b=>b.Owner)
                .Include(b => b.BookReservations)
                    .ThenInclude(br => br.Reservation)
                    .ThenInclude(br => br.Apartment)
                .FirstOrDefault();

            if(list == null)
                return NotFound();

            return View(list);

        }

        // GET: BookingLists/Details/5
        public async Task<IActionResult> Details(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var bookingList = await _context.BookingLists
                .Include(b => b.Owner)
                .FirstOrDefaultAsync(m => m.Id == id);
            if (bookingList == null)
            {
                return NotFound();
            }

            return View(bookingList);
        }

        // GET: Reservations/Create
        public IActionResult Create() {
            ViewData["ApartmentId"] = new SelectList(_context.Apartments, "Id", "ApartmentName");
            return View();
        }

        // POST: Reservations/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,Check_in_date,ApartmentId")] Reservation reservation, int nights) {
            if(ModelState.IsValid) {
                var userId = User.FindFirstValue(ClaimTypes.NameIdentifier);
                var loggedinUser = _context.Users.Find(userId);
                reservation.User = loggedinUser;
                reservation.Id = Guid.NewGuid();
                _context.Add(reservation);
                BookingList list = _context.BookingLists.Where(u => u.OwnerId == userId).Include(r => r.BookReservations).FirstOrDefault();
                if(list == null) {

                    list = new BookingList {
                        Id = Guid.NewGuid(),
                        OwnerId = userId,
                        Owner = loggedinUser,
                    };
                    _context.BookingLists.Add(list);
                }

                BookReservation reservations = new BookReservation {
                        Id = Guid.NewGuid(),
                        ReservationId = reservation.Id,
                        Reservation = reservation,
                        BookingListId = list.Id,
                        BookingList = list,
                        NumberOfNights = nights,
                    };
                _context.BookReservations.Add(reservations);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            ViewData["ApartmentId"] = new SelectList(_context.Apartments, "Id", "ApartmentName", reservation.ApartmentId);
            return View(reservation);
        }

        // GET: BookingLists/Edit/5
        public async Task<IActionResult> Edit(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var bookingList = await _context.BookingLists.FindAsync(id);
            if (bookingList == null)
            {
                return NotFound();
            }
            ViewData["OwnerId"] = new SelectList(_context.Users, "Id", "Id", bookingList.OwnerId);
            return View(bookingList);
        }

        // POST: BookingLists/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(Guid id, [Bind("Id,OwnerId")] BookingList bookingList)
        {
            if (id != bookingList.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(bookingList);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!BookingListExists(bookingList.Id))
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
            ViewData["OwnerId"] = new SelectList(_context.Users, "Id", "Id", bookingList.OwnerId);
            return View(bookingList);
        }

        // GET: Reservations/Delete/5
        public async Task<IActionResult> Delete(Guid? id) {
            if(id == null) {
                return NotFound();
            }

            var reservation = await _context.Reservations
                .Include(r => r.Apartment)
                .FirstOrDefaultAsync(m => m.Id == id);
            if(reservation == null) {
                return NotFound();
            }
            ViewData["id"]=id;
            return View(reservation);
        }

        // POST: Reservations/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(Guid id) {
            var reservation = await _context.BookReservations.FindAsync(id);
            if(reservation != null) {
                _context.BookReservations.Remove(reservation);
            }

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool BookingListExists(Guid id)
        {
            return _context.BookingLists.Any(e => e.Id == id);
        }



    }
}
