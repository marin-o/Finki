using BA.Domain.Models;
using BA.Repository.Interface;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BA.Repository.Implementation
{
    public class BookingListRepository : IBookingListRepository
    {
        private readonly ApplicationDbContext context;
        private DbSet<BookingList> entities;

        public BookingListRepository(ApplicationDbContext context)
        {
            this.context = context;
            entities = context.Set<BookingList>();
        }
        public List<BookingList> GetAllOrders()
        {
            return entities
                .Include(z => z.BookReservations)
                .ThenInclude(br => br.Reservation)
                .Include(z => z.User)
                .ToList();
        }

        public BookingList GetDetailsForOrder(BaseEntity id)
        {
            return entities
                .Include(z => z.BookReservations)
                .ThenInclude(br => br.Reservation)
                .Include(z => z.User)
                .SingleOrDefaultAsync(z => z.Id == id.Id).Result;
        }
    }
}
